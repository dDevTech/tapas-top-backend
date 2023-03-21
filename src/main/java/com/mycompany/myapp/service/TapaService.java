package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.repository.TapaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TapaService {
    private final Logger log = LoggerFactory.getLogger(TapaService.class);

    @Autowired
    private TapaRepository tapaRepository;

    public List<Tapa> findAll() {
        return tapaRepository.findAll();
    }

    public Tapa findById(Long id) {
        return tapaRepository.findById(id).orElse(null);
    }

    public Tapa save(Tapa tapa) {
        return tapaRepository.save(tapa);
    }

    public void deleteById(Long id) {
        tapaRepository.deleteById(id);
    }

}
