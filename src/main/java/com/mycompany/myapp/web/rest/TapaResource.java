package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.TapaService;
import com.mycompany.myapp.service.dto.TapaCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tapa")
public class TapaResource {

    private final Logger log = LoggerFactory.getLogger(TapaResource.class);

    private final TapaService tapaService;

    private final TapaRepository tapaRepository;
    private final EstablishmentRepository establishmentRepository;

    public TapaResource(TapaRepository tapaRepository, TapaService tapaService, EstablishmentRepository establishmentRepository) {
        this.tapaRepository = tapaRepository;
        this.tapaService = tapaService;
        this.establishmentRepository = establishmentRepository;
    }

    @GetMapping("")
    public List<Tapa> findAll(){
        return this.tapaService.findAll();
    }


    @GetMapping("/{id}")
    public Tapa findById(@PathVariable("id") Long id){
        return this.tapaService.findById(id);
    }

    @PostMapping("")
    public Tapa save(@RequestBody TapaCreationDTO tapa){
        Establishment establishment;
        System.out.println(tapa.toString());
        if((establishment = this.establishmentRepository.findById(tapa.getEstablishment()).get()) == null){
            return null;
        }
        Tapa t = tapa.toTapa();
        t.setEstablishment(establishment);
        return this.tapaService.save(t);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.tapaService.deleteById(id);
    }
}
