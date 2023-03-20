package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.repository.EstablishmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentService {
    private final Logger log = LoggerFactory.getLogger(EstablishmentService.class);

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public Establishment findById(Long id) {
        return establishmentRepository.findById(id).orElse(null);
    }

    public Establishment save(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    public void deleteById(Long id) {
        establishmentRepository.deleteById(id);
    }
}
