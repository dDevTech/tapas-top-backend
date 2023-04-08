package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.service.dto.User_RatingDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserService {

    private final Logger log = LoggerFactory.getLogger(MyUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TapaRepository tapaRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private User_RatingService user_ratingService;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<TapaDTO> getFavourites(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        List<TapaDTO> tapaDTOList = user
            .map(User::getFavourites)
            .orElse(Collections.emptySet())
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), user_ratingService.getTapaRatingAverage(tapa.getId()), null);
                User_RatingDTO ratingDTO = new User_RatingDTO(user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.get().getId()));
                tapaDTO.setRating(ratingDTO);
                return tapaDTO;
            })
            .collect(Collectors.toList());
        return tapaDTOList;
    }

    public List<TapaDTO> getLastTapas(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        Instant today = Instant.now();

        List<Tapa> tapas = tapaRepository.findAllByMyCreatedByOrderByCreatedDateDesc(user.get().getId());

        List<TapaDTO> res = tapas
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), user_ratingService.getTapaRatingAverage(tapa.getId()), null);
                User_RatingDTO ratingDTO = new User_RatingDTO(user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.get().getId()));
                tapaDTO.setRating(ratingDTO);
                return tapaDTO;
            })
            .filter(tapaDTO -> tapaDTO.getCreatedDate().isAfter(Instant.from(today.minus(7, ChronoUnit.DAYS))))
            .collect(Collectors.toList());
        return res;
    }


    public List<EstablishmentDTO> getAllRestaurants(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        List<Establishment> establishments = establishmentRepository.findAllByMyCreatedByOrderByCreatedDateDesc(user.get().getId());

        List<EstablishmentDTO> res = establishments
            .stream()
            .map(establishment -> {
                return new EstablishmentDTO(establishment, establishment.getAddress(), null);
            })
            .collect(Collectors.toList());

        return res;
    }




    public List<EstablishmentDTO> getLastRestaurants(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        Instant today = Instant.now();

        List<EstablishmentDTO> establishmentDTOList = establishmentRepository
            .findAllByMyCreatedByOrderByCreatedDateDesc(user.get().getId())
            .stream()
            .map(establishment -> {
                return new EstablishmentDTO(establishment, establishment.getAddress(), null);
            })
            .filter(tapaDTO -> tapaDTO.getCreatedDate().isAfter(Instant.from(today.minus(7, ChronoUnit.DAYS))))
            .collect(Collectors.toList());

        return establishmentDTOList;
    }
}
