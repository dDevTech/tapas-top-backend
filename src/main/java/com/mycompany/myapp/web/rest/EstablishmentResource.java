package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.service.EstablishmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establishment")
public class EstablishmentResource {

    private final Logger log = LoggerFactory.getLogger(EstablishmentResource.class);

    private final EstablishmentService establishmentService;

    private final EstablishmentRepository establishmentRepository;

    public EstablishmentResource(EstablishmentRepository establishmentRepository, EstablishmentService establishmentService) {
        this.establishmentRepository = establishmentRepository;
        this.establishmentService = establishmentService;
    }

    @GetMapping("")
    public List<Establishment> findAll(){
        return this.establishmentService.findAll();
    }


    @GetMapping("/{id}")
    public Establishment findById(@PathVariable("id") Long id){
        return this.establishmentService.findById(id);
    }

    @PostMapping("")
    public Establishment save(@RequestBody Establishment Establishment){
        return this.establishmentService.save(Establishment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.establishmentService.deleteById(id);
    }
}
