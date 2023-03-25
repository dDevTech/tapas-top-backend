package com.mycompany.myapp.service;

import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.repository.User_RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    private final Logger log = LoggerFactory.getLogger(MyUserService.class);

    @Autowired
    private UserRepository userRepository;
}
