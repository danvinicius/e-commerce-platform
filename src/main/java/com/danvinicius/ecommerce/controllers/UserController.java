package com.danvinicius.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danvinicius.ecommerce.dto.user.UserRequestDTO;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDTO data) {
        User user = this.userService.createUser(data);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> createAdminUser(@Valid @RequestBody UserRequestDTO data) {
        User user = this.userService.createAdminUser(data);
        return ResponseEntity.ok().body(user);
    }
}
