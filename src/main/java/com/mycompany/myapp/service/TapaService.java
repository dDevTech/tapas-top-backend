package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.filters.TapaFilter;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TapaService {

    private final Logger log = LoggerFactory.getLogger(TapaService.class);

    @Autowired
    private TapaRepository tapaRepository;

    public List<Tapa> findAll(Map<String, String> filters) {
        return TapaFilter.filterTapas(tapaRepository.findAll(), filters);
    }

    public Tapa findById(Long id) {
        return tapaRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestAlertException("Could not found Tapa with id: " + id, "Invalid id", "Invalid id"));
    }

    public Tapa save(Tapa tapa) {
        return tapaRepository.save(tapa);
    }

    public void deleteById(Long id) {
        tapaRepository.deleteById(id);
    }

    public List<Tapa> findByName(String name, Map<String, String> filters) {
        return TapaFilter.filterTapas(tapaRepository.findAllByNameLike("%" + name + "%"), filters);
    }
}
