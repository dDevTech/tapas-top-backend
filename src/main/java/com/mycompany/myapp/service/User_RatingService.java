package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.repository.User_RatingRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.dto.User_RatingDTO;
import com.mycompany.myapp.web.rest.AccountResource;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class User_RatingService {

    private final Logger log = LoggerFactory.getLogger(User_RatingService.class);

    @Autowired
    private User_RatingRepository userRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TapaRepository tapaRepository;

    public List<User_RatingDTO> findAll() {
        return userRatingRepository
            .findAll()
            .stream()
            .map(userRating -> new User_RatingDTO(userRating, userRating.getUser(), userRating.getTapa()))
            .collect(Collectors.toList());
    }

    public User_RatingDTO findById(Long id) {
        User_Rating userRating = userRatingRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestAlertException("Invalid user rating id", "Invalid id", "Invalid id"));
        return new User_RatingDTO(userRating, userRating.getUser(), userRating.getTapa());
    }

    public User_RatingDTO save(User_Rating userRating, Long userId, Long tapaId) {
        userRating.setTapa(
            tapaRepository
                .findById(tapaId)
                .orElseThrow(() -> new BadRequestAlertException("Tapa id was not found", "Invalid tapa id", "Invalid tapa id"))
        );
        userRating.setUser(
            userRepository
                .findById(userId)
                .orElseThrow(() -> new BadRequestAlertException("User id was not found", "Invalid user id", "Invalid user id"))
        );
        userRating.setCreatedBy(userRating.getUser().getLogin());

        List<User_Rating> prevRatings = userRatingRepository.findAllByUserIdAndTapaId(userId, tapaId);
        if (prevRatings != null && !prevRatings.isEmpty()) userRating.setId(prevRatings.get(0).getId());

        User_Rating saved = userRatingRepository.save(userRating);
        return new User_RatingDTO(saved, saved.getUser(), saved.getTapa());
    }

    public void deleteById(Long id) {
        userRatingRepository.deleteById(id);
    }

    public Double getTapaRatingAverage(Long id) {
        return userRatingRepository.findAllByTapaId(id).stream().mapToDouble(userRating -> userRating.getRating()).average().orElse(0);
    }

    public User_Rating findByTapaIdAndUserId(Long id_tapa, Long id_usuario) {
        return userRatingRepository.findAllByUserIdAndTapaId(id_usuario, id_tapa).get(0);
    }
}
