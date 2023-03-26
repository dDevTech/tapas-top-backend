package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.TapaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tapa")
public class TapaResource {

    private final Logger log = LoggerFactory.getLogger(TapaResource.class);

    private final TapaService tapaService;

    private final TapaRepository tapaRepository;

    public TapaResource(TapaRepository tapaRepository, TapaService tapaService) {
        this.tapaRepository = tapaRepository;
        this.tapaService = tapaService;
    }
}
