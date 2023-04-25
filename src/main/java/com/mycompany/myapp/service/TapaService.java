package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.service.filters.TapaFilter;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    private User_RatingService user_ratingService;

    @Autowired
    private TapaRepository tapaRepository;

    public List<TapaDTO> findAll(Map<String, String> filters) {
        List<Tapa> tapas = TapaFilter.filterTapas(tapaRepository.findAll(), filters);
        List<TapaDTO> res = tapas
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, null, tapa.getRatings(), null);
                EstablishmentDTO establishmentDTO = new EstablishmentDTO(
                    tapa.getEstablishment(),
                    tapa.getEstablishment().getAddress(),
                    null
                );
                tapaDTO.setEstablishment(establishmentDTO);
                return tapaDTO;
            })
            .collect(Collectors.toList());
        res.sort(Comparator.comparingDouble(TapaDTO::getAverage));
        Collections.reverse(res);
        return res;
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
