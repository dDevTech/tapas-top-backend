package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.service.dto.User_RatingDTO;
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

    @Autowired
    private UserService userService;

    public List<TapaDTO> findAll(Map<String, String> filters) {
        User user = userService
            .getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("Could not found users login", "Login not found", "Login not found"));

        List<Tapa> tapas = TapaFilter.filterTapas(tapaRepository.findAll(), filters);
        List<TapaDTO> res = tapas
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), tapa.getRatings());
                User_Rating rating = user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.getId());
                if (rating != null) {
                    User_RatingDTO ratingDTO = new User_RatingDTO(rating);
                    tapaDTO.setRating(ratingDTO);
                }
                tapaDTO.setFavourite(tapa.getFans().stream().filter(fan -> fan.getId().equals(user.getId())).count() > 0);
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
