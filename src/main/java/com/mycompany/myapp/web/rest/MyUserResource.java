package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.MyUserService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/allTapas/{login}")
    public ResponseEntity<List<TapaDTO>> getAllTapas(@PathVariable String login) {
        List<TapaDTO> establishmentDTOList = myUserService.getAllTapas(login);
        return ResponseEntity.ok(establishmentDTOList);
    }

    @GetMapping("/lastTapas/{login}")
    public ResponseEntity<List<TapaDTO>> getLastTapas(@PathVariable String login) {
        List<TapaDTO> tapaDTOList = myUserService.getLastTapas(login);
        return ResponseEntity.ok(tapaDTOList);
    }

    @GetMapping("/allRestaurants/{login}")
    public ResponseEntity<List<EstablishmentDTO>> getAllRestaurants(@PathVariable String login) {
        List<EstablishmentDTO> establishmentDTOList = myUserService.getAllRestaurants(login);
        return ResponseEntity.ok(establishmentDTOList);
    }

    @GetMapping("/lastRestaurants/{login}")
    public ResponseEntity<List<EstablishmentDTO>> getLastRestaurants(@PathVariable String login) {
        List<EstablishmentDTO> restaurants = myUserService.getLastRestaurants(login);
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/favourites/{tapaId}")
    public ResponseEntity addTapaToFavourites(@PathVariable Long tapaId) {
        myUserService.addTapaToFavourites(tapaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favourites/{tapaId}")
    public ResponseEntity removeTapaFromFavourites(@PathVariable Long tapaId) {
        myUserService.removeTapaFromFavourites(tapaId);
        return ResponseEntity.ok().build();
    }
}
