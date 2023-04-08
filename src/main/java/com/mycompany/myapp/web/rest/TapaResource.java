package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.TapaService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.TapaCreationDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tapa")
public class TapaResource {

    private final Logger log = LoggerFactory.getLogger(TapaResource.class);

    private final TapaService tapaService;

    private final TapaRepository tapaRepository;
    private final EstablishmentRepository establishmentRepository;

    private final UserService userService;

    public TapaResource(TapaRepository tapaRepository, TapaService tapaService, EstablishmentRepository establishmentRepository, UserService userService) {
        this.tapaRepository = tapaRepository;
        this.tapaService = tapaService;
        this.establishmentRepository = establishmentRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public List<TapaDTO> findAll(){
        List<Tapa> tapaList = this.tapaService.findAll();
        List<TapaDTO> dtos = new ArrayList<>();
        for(Tapa tapa : tapaList){
            dtos.add(new TapaDTO(tapa));
        }
        return dtos;
    }


    @GetMapping("/{id}")
    public TapaDTO findById(@PathVariable("id") Long id){
        Tapa tapa = this.tapaService.findById(id);
        if (tapa == null)
            return null;
        return new TapaDTO(tapa);
    }

    @PostMapping("")
    public TapaDTO save(@RequestBody TapaCreationDTO dto){
        Establishment establishment;
        System.out.println(dto.toString());
        if((establishment = this.establishmentRepository.findById(dto.getEstablishment()).get()) == null){
            return null;
        }
        Optional<User> isUser = userService.getUserWithAuthorities();
        if(!isUser.isPresent()){
            return null;
        }
        User user = isUser.get();
        establishment.setMyCreatedBy(user.getId());
        Tapa tapa = dto.toTapa();
        tapa.setEstablishment(establishment);
        tapa.setMyCreatedBy(user.getId());
        return new TapaDTO(this.tapaService.save(tapa));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.tapaService.deleteById(id);
    }
}
