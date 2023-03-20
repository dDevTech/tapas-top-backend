package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.User_RatingRepository;
import com.mycompany.myapp.service.User_RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class User_RatingResource {

    private final Logger log = LoggerFactory.getLogger(User_RatingResource.class);

    private final User_RatingService user_RatingService;

    private final User_RatingRepository user_RatingRepository;


    public User_RatingResource(User_RatingRepository user_RatingRepository, User_RatingService user_RatingService){
        this.user_RatingRepository = user_RatingRepository;
        this.user_RatingService = user_RatingService;
    }

}
