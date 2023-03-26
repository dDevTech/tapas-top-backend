package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.User_Rating;
import com.mycompany.myapp.repository.User_RatingRepository;
import java.util.List;
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

    public List<User_Rating> findAll() {
        return userRatingRepository.findAll();
    }

    public User_Rating findById(Long id) {
        return userRatingRepository.findById(id).orElse(null);
    }

    public User_Rating save(User_Rating userRating) {
        return userRatingRepository.save(userRating);
    }

    public void deleteById(Long id) {
        userRatingRepository.deleteById(id);
    }
}
