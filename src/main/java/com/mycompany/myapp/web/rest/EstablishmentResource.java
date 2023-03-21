package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.service.EstablishmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/establishment")
public class EstablishmentResource {
    private final Logger log = LoggerFactory.getLogger(EstablishmentResource.class);

    private final EstablishmentService establishmentService;

    private final EstablishmentRepository establishmentRepository;


    public EstablishmentResource(EstablishmentRepository establishmentRepository, EstablishmentService establishmentService){
        this.establishmentRepository = establishmentRepository;
        this.establishmentService = establishmentService;
    }
}
