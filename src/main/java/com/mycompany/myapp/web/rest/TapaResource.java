package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.TapaService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.User_RatingService;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.service.dto.User_RatingDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.requests.TapaRequest;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<TapaDTO> findAll(
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "precedence", required = false) String precedence,
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "country", required = false) String country
    ) {
        if (city == null) city = "";
        if (precedence == null) precedence = "";
        if (type == null) type = "";
        if (country == null) country = "";
        Map<String, String> params = Map.of("city", city, "precedence", precedence, "type", type, "country", country);
        List<TapaDTO> dtos = this.tapaService.findAll(params);
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
    public List<TapaDTO> tapaByName(
        @PathVariable String name,
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "precedence", required = false) String precedence,
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "country", required = false) String country
    ) {
        User user = SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneWithAuthoritiesByLogin)
            .orElseThrow(() -> new BadRequestAlertException("Could not found user with login", "Invalid login", "Invalid login"));
        if (city == null) city = "";
        if (precedence == null) precedence = "";
        if (type == null) type = "";
        if (country == null) country = "";
        Map<String, String> params = Map.of("city", city, "precedence", precedence, "type", type, "country", country);

        return tapaService
            .findByName(name, params)
            .stream()
            .map(tapa -> {
                TapaDTO dto = new TapaDTO(tapa, tapa.getEstablishment(), tapa.getRatings());
                User_Rating rating = user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.getId());
                if (rating != null) {
                    User_RatingDTO ratingDTO = new User_RatingDTO(rating);
                    dto.setRating(ratingDTO);
                }
                dto.setFavourite(tapa.getFans().stream().filter(fan -> fan.getId().equals(user.getId())).count() > 0);
                return dto;
            })
            .collect(Collectors.toList());
    }
}
