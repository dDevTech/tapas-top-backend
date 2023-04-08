package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Establishment;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.EstablishmentRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.EstablishmentService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.dto.EstablishmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/establishment")
public class EstablishmentResource {

    private final Logger log = LoggerFactory.getLogger(EstablishmentResource.class);

    private final EstablishmentService establishmentService;

    private final EstablishmentRepository establishmentRepository;

    private final UserService userService;

    public EstablishmentResource(EstablishmentRepository establishmentRepository, EstablishmentService establishmentService, UserService userService) {
        this.establishmentRepository = establishmentRepository;
        this.establishmentService = establishmentService;
        this.userService = userService;
    }

    @GetMapping("")
    public List<EstablishmentDTO> findAll(){
        return this.establishmentService.findAll();
    }


    @GetMapping("/{id}")
    public EstablishmentDTO findById(@PathVariable("id") Long id){
        return this.establishmentService.findById(id);
    }

    @PostMapping("")
    public EstablishmentDTO save(@RequestBody EstablishmentDTO dto){
        Establishment establishment = dto.toEstablishment();
        Optional<User> isUser = userService.getUserWithAuthorities();
        if(!isUser.isPresent()){
            return null;
        }
        User user = isUser.get();
        establishment.setMyCreatedBy(user.getId());

        return new EstablishmentDTO(this.establishmentService.save(establishment));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.establishmentService.deleteById(id);
    }
}
