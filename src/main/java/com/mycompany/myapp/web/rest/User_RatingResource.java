package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.TapaRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.repository.User_RatingRepository;
import com.mycompany.myapp.service.User_RatingService;
import com.mycompany.myapp.service.dto.User_RatingDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.requests.User_RatingRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class User_RatingResource {

    private final Logger log = LoggerFactory.getLogger(User_RatingResource.class);

    @Autowired
    private User_RatingService user_RatingService;

    @Autowired
    private User_RatingRepository user_RatingRepository;

    @Autowired
    private TapaRepository tapaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User_RatingDTO> findAll() {
        return user_RatingService.findAll();
    }

    @GetMapping("/{id}")
    public User_RatingDTO findById(@PathVariable("id") Long id) {
        return user_RatingService.findById(id);
    }

    @PostMapping("")
    public User_RatingDTO save(@RequestBody User_RatingRequest userRating) {
        User_Rating entity = new User_Rating();
        entity.setRating(userRating.getRating());
        return user_RatingService.save(entity, userRating.getUserId(), userRating.getTapaId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        user_RatingService.deleteById(id);
    }
}
