package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.Tapa;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.service.dto.User_RatingDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), tapa.getRatings());
                User_Rating rating = user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.get().getId());
                if (rating != null) {
                    User_RatingDTO ratingDTO = new User_RatingDTO(rating);
                    tapaDTO.setRating(ratingDTO);
                }
                tapaDTO.setFavourite(tapa.getFans().stream().filter(fan -> fan.getId().equals(user.get().getId())).count() > 0);
                return tapaDTO;
            })
            .collect(Collectors.toList());
        return tapaDTOList;
    }

    public List<TapaDTO> getAllTapas(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        //Tapas creadas por el usuario
        List<Tapa> tapas = tapaRepository.findAllByMyCreatedByOrderByCreatedDateDesc(user.get().getId());

        //Tapas valoradas por el usuario
        for (Tapa tapa : user_ratingService.findAllTapasRatedByUser(user.get().getId())) {
            if (!tapas.contains(tapa)) tapas.add(tapa);
        }

        //Ordenar tapas por creacion o valoracion
        tapas =
            tapas
                .stream()
                .sorted((t1, t2) -> {
                    Instant i1 = t1.getCreatedDate();
                    List<User_Rating> userRatings1 = t1
                        .getRatings()
                        .stream()
                        .filter(r1 -> r1.getUser().getId().equals(user.get().getId()))
                        .collect(Collectors.toList());
                    if (userRatings1.size() > 0) i1 = userRatings1.get(0).getCreatedDate();
                    Instant i2 = t2.getCreatedDate();
                    List<User_Rating> userRatings2 = t2
                        .getRatings()
                        .stream()
                        .filter(r2 -> r2.getUser().getId().equals(user.get().getId()))
                        .collect(Collectors.toList());
                    if (userRatings2.size() > 0) i2 = userRatings2.get(0).getCreatedDate();
                    return i2.compareTo(i1);
                })
                .collect(Collectors.toList());

        //Crear DTOs
        List<TapaDTO> res = tapas
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), tapa.getRatings());
                User_Rating rating = user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.get().getId());
                if (rating != null) {
                    User_RatingDTO ratingDTO = new User_RatingDTO(rating);
                    tapaDTO.setRating(ratingDTO);
                }
                tapaDTO.setFavourite(tapa.getFans().stream().filter(fan -> fan.getId().equals(user.get().getId())).count() > 0);
                return tapaDTO;
            })
            .collect(Collectors.toList());

        return res;
    }

    public List<TapaDTO> getLastTapas(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        Instant today = Instant.now();

        //Tapas creadas por el usuario en ultimos 7 dias
        List<Tapa> tapas = tapaRepository
            .findAllByMyCreatedByOrderByCreatedDateDesc(user.get().getId())
            .stream()
            .filter(tapa -> tapa.getCreatedDate().isAfter(Instant.from(today.minus(7, ChronoUnit.DAYS))))
            .collect(Collectors.toList());

        //Tapas valoradas por el usuario en ultimos 7 dias
        for (Tapa tapa : user_ratingService.findLastTapasRatedByUser(user.get().getId())) {
            if (!tapas.contains(tapa)) tapas.add(tapa);
        }

        //Ordenar tapas por creacion o valoracion
        tapas =
            tapas
                .stream()
                .sorted((t1, t2) -> {
                    Instant i1 = t1.getCreatedDate();
                    List<User_Rating> userRatings1 = t1
                        .getRatings()
                        .stream()
                        .filter(r1 -> r1.getUser().getId().equals(user.get().getId()))
                        .collect(Collectors.toList());
                    if (userRatings1.size() > 0) i1 = userRatings1.get(0).getCreatedDate();
                    Instant i2 = t2.getCreatedDate();
                    List<User_Rating> userRatings2 = t2
                        .getRatings()
                        .stream()
                        .filter(r2 -> r2.getUser().getId().equals(user.get().getId()))
                        .collect(Collectors.toList());
                    if (userRatings2.size() > 0) i2 = userRatings2.get(0).getCreatedDate();
                    return i2.compareTo(i1);
                })
                .collect(Collectors.toList());

        //Crear DTOs
        List<TapaDTO> res = tapas
            .stream()
            .map(tapa -> {
                TapaDTO tapaDTO = new TapaDTO(tapa, tapa.getEstablishment(), tapa.getRatings());
                User_Rating rating = user_ratingService.findByTapaIdAndUserId(tapa.getId(), user.get().getId());
                if (rating != null) {
                    User_RatingDTO ratingDTO = new User_RatingDTO(rating);
                    tapaDTO.setRating(ratingDTO);
                }
                tapaDTO.setFavourite(tapa.getFans().stream().filter(fan -> fan.getId().equals(user.get().getId())).count() > 0);
                return tapaDTO;
            })
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
            .sorted((e1, e2) -> {
                return e2.getCreatedDate().compareTo(e2.getCreatedDate());
            })
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
            .filter(establishment -> establishment.getCreatedDate().isAfter(Instant.from(today.minus(7, ChronoUnit.DAYS))))
            .sorted((e1, e2) -> {
                return e2.getCreatedDate().compareTo(e2.getCreatedDate());
            })
            .map(establishment -> {
                return new EstablishmentDTO(establishment, establishment.getAddress(), null);
            })
            .collect(Collectors.toList());

        return establishmentDTOList;
    }

    public void addTapaToFavourites(Long tapaId) {
        User user = userService
            .getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("Could not found users login", "Login not found", "Login not found"));

        user = userRepository.findById(user.getId()).get(); //solucion cutre pero que funciona

        Optional<Tapa> tapaNew = tapaRepository.findById(tapaId);
        if (!tapaNew.isPresent()) {
            throw new BadRequestAlertException("Could not found tapa with id: " + tapaId, "Invalid tapa", "Invalid tapa");
        }

        Set<Tapa> favourites = user.getFavourites();
        favourites.add(tapaNew.get());
        user.setFavourites(favourites);
        userRepository.save(user);
    }

    public void removeTapaFromFavourites(Long tapaId) {
        User user = userService
            .getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("Could not found users login", "Login not found", "Login not found"));

        user = userRepository.findById(user.getId()).get(); //solucion cutre pero que funciona

        Optional<Tapa> tapaToRemove = tapaRepository.findById(tapaId);
        if (!tapaToRemove.isPresent()) {
            throw new BadRequestAlertException("Could not found tapa with id: " + tapaId, "Invalid tapa", "Invalid tapa");
        }

        Set<Tapa> favourites = user.getFavourites();
        if (user.getFavourites().stream().noneMatch(tapa -> Objects.equals(tapa.getId(), tapaToRemove.get().getId()))) {
            throw new BadRequestAlertException(
                "Could not found tapa in user favourites with id: " + tapaId,
                "Invalid tapa",
                "Invalid tapa"
            );
        }

        favourites =
            favourites.stream().filter(tapa -> !Objects.equals(tapa.getId(), tapaToRemove.get().getId())).collect(Collectors.toSet());
        user.setFavourites(favourites);
        userRepository.save(user);
    }
}
