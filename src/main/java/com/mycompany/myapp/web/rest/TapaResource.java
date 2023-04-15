package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.TapaService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.User_RatingService;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.requests.TapaRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tapa")
public class TapaResource {

    private final Logger log = LoggerFactory.getLogger(TapaResource.class);

    @Autowired
    private TapaService tapaService;

    @Autowired
    private TapaRepository tapaRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private User_RatingService user_ratingService;

    @GetMapping("")
    public List<TapaDTO> findAll() {
        List<Tapa> tapaList = this.tapaService.findAll();
        List<TapaDTO> dtos = new ArrayList<>();
        for (Tapa tapa : tapaList) {
            dtos.add(new TapaDTO(tapa, tapa.getEstablishment(), user_ratingService.getTapaRatingAverage(tapa.getId()), null));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public TapaDTO findById(@PathVariable("id") Long id) {
        Tapa tapa = this.tapaService.findById(id);
        if (tapa == null) return null;
        return new TapaDTO(tapa);
    }

    @PostMapping("")
    public TapaDTO save(@RequestBody TapaRequest dto) {
        System.out.println(dto.toString());
        Establishment establishment =
            this.establishmentRepository.findById(dto.getEstablishment())
                .orElseThrow(() ->
                    new BadRequestAlertException(
                        "Could not found Establishment with id: " + dto.getEstablishment(),
                        "Invalid id",
                        "Invalid id"
                    )
                );

        User user = userService
            .getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("Could not found users login", "Login not found", "Login not found"));

        Tapa tapa = dto.toTapa();
        tapa.setEstablishment(establishment);
        tapa.setMyCreatedBy(user.getId());
        return new TapaDTO(this.tapaService.save(tapa));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.tapaService.deleteById(id);
    }

    @GetMapping("/name/{name}")
    public List<TapaDTO> tapaById(@PathVariable String name) {
        return tapaService
            .findByName(name)
            .stream()
            .map(tapa -> new TapaDTO(tapa, tapa.getEstablishment(), user_ratingService.getTapaRatingAverage(tapa.getId()), null))
            .collect(Collectors.toList());
    }
}
