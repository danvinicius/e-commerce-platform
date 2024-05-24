package com.danvinicius.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danvinicius.ecommerce.dto.user.AuthenticationDTO;
import com.danvinicius.ecommerce.dto.user.CreateUserDTO;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.services.AuthenticationService;
import com.danvinicius.ecommerce.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/auth")
public class UserController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Void> login(@Valid @RequestBody AuthenticationDTO data) {
        authenticationService.authenticate(data);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody CreateUserDTO data) {
        User user = this.userService.createUser(data);
        return ResponseEntity.ok().body(user);
    }
}
