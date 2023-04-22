package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.MyUserService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import com.mycompany.myapp.service.dto.TapaDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity<List<TapaDTO>> addTapaToFavourites(@PathVariable Long tapaId, @AuthenticationPrincipal(expression = "user") User currentUser) {
        Optional<Tapa> tapa = myUserService.getTapaById(tapaId);
        if (!tapa.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tapa not found");
        }

        Optional<User> user = userRepository.findById(currentUser.getId());
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }

        Set<Tapa> favourites = user.get().getFavourites();
        favourites.add(tapa.get());
        user.get().setFavourites(favourites);
        userRepository.save(user.get());

        List<TapaDTO> tapaDTOList = myUserService.getFavourites(currentUser.getLogin());
        return ResponseEntity.ok(tapaDTOList);
    }

    public ResponseEntity<List<TapaDTO>> removeTapaFromFavourites(@PathVariable Long tapaId, @AuthenticationPrincipal(expression = "user") User currentUser) {
        Optional<Tapa> tapa = myUserService.getTapaById(tapaId);
        if (!tapa.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tapa not found");
        }

        Optional<User> user = userRepository.findById(currentUser.getId());
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }

        Set<Tapa> favourites = user.get().getFavourites();
        if (!favourites.contains(tapa.get())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tapa not in favourites");
        }

        favourites.remove(tapa.get());
        user.get().setFavourites(favourites);
        userRepository.save(user.get());

        List<TapaDTO> tapaDTOList = myUserService.getFavourites(currentUser.getLogin());
        return ResponseEntity.ok(tapaDTOList);
    }
}
