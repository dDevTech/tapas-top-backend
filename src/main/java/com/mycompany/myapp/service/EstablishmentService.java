package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstablishmentService {

    private final Logger log = LoggerFactory.getLogger(EstablishmentService.class);

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<EstablishmentDTO> findAll() {
        List<Establishment> aux = establishmentRepository.findAll();
        List<EstablishmentDTO> list = new ArrayList<>(aux.size());
        for (Establishment establishment : aux) {
            list.add(new EstablishmentDTO(establishment, establishment.getAddress(), establishment.getTapas()));
        }
        return list;
    }

    public EstablishmentDTO findById(Long id) {
        Establishment establishment = establishmentRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestAlertException("Could not found Establishmen with id: " + id, "Invalid id", "Invalid id"));
        return new EstablishmentDTO(establishment, establishment.getAddress(), establishment.getTapas());
    }

    public Establishment save(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    public void deleteById(Long id) {
        establishmentRepository.deleteById(id);
    }
}
