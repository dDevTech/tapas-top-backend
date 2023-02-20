package dev.gpi.g5.tapastopbackend.controllers;

import dev.gpi.g5.tapastopbackend.controllers.responses.TapaResponse;
import dev.gpi.g5.tapastopbackend.models.Tapa;
import dev.gpi.g5.tapastopbackend.services.tapas.TapasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/tapas")
public class TapasController {

    @Autowired
    private TapasService service;

    @GetMapping("/{id}")
    public ResponseEntity<TapaResponse> tapaByID(@PathVariable Long id){
        Tapa tapa = service.findById(id);
        TapaResponse response = null;

        if(tapa != null)
            response = new TapaResponse(tapa);
        return new ResponseEntity<TapaResponse>(response, HttpStatus.OK);
    }
}
