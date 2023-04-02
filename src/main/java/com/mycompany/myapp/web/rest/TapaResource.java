package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.TapaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<Tapa> findAll(){
        return this.tapaService.findAll();
    }


    @GetMapping("/byId")
    public Tapa findById(@RequestParam Long id){
        return this.tapaService.findById(id);
    }

    @PostMapping("/save")
    public Tapa save(@RequestBody Tapa tapa){
        return this.tapaService.save(tapa);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Long id){
        this.tapaService.deleteById(id);
    }
}
