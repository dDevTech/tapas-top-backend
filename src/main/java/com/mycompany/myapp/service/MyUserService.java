package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
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
    private UserService userService;

    public List<TapaDTO> getFavourites(String login) {
        Optional<User> user = userRepository.findOneByLogin(login);

        if (!user.isPresent()) {
            throw new BadRequestAlertException("Could not found user with login: " + login, "Invalid login", "Invalid login");
        }

        List<TapaDTO> tapaDTOList = user
            .map(User::getFavourites)
            .orElse(Collections.emptySet())
            .stream()
            .map(TapaDTO::new)
            .collect(Collectors.toList());
        return tapaDTOList;
    }
}