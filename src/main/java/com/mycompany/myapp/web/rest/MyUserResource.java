package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.MyUserService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.TapaDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myuser")
public class MyUserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/favourites/{login}")
    public ResponseEntity<List<TapaDTO>> getFavouritesTapas(@PathVariable String login) {
        List<TapaDTO> tapaDTOList = myUserService.getFavourites(login);
        return ResponseEntity.ok(tapaDTOList);
    }

    @GetMapping("/lastRestaurants/{userId}")
    public ResponseEntity<List<String>> getLastRestaurants(@PathVariable Long userId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
        List<EstablishmentDTO> restaurantNames = myUserService.getLastRestaurants(userId, fromDate);
        return ResponseEntity.ok(restaurantNames);
    }
}
