package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myuser")
public class MyUserResource {

    @Autowired
    private MyUserService myUserService;
}
