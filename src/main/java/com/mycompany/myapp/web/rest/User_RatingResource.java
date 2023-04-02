package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.User_RatingRepository;
import com.mycompany.myapp.service.User_RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class User_RatingResource {

    private final Logger log = LoggerFactory.getLogger(User_RatingResource.class);

    private final User_RatingService user_RatingService;

    private final User_RatingRepository user_RatingRepository;

    public User_RatingResource(User_RatingRepository user_RatingRepository, User_RatingService user_RatingService) {
        this.user_RatingRepository = user_RatingRepository;
        this.user_RatingService = user_RatingService;
    }

    @RequestMapping(value = "/user-ratings", method = RequestMethod.GET)
    public List<User_Rating> findAll() {
        return user_RatingService.findAll();
    }

    @RequestMapping(value = "/user-ratings/{id}", method = RequestMethod.GET)
    public User_Rating findById(@PathVariable("id") Long id) {
        return user_RatingService.findById(id);
    }

    @RequestMapping(value = "/user-ratings", method = RequestMethod.POST)
    public User_Rating save(@RequestBody User_Rating userRating) {
        return user_RatingService.save(userRating);
    }

    @RequestMapping(value = "/user-ratings/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        user_RatingService.deleteById(id);
    }

}
