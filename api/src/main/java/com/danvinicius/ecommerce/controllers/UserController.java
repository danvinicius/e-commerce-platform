package com.danvinicius.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danvinicius.ecommerce.dto.auth.AuthenticationRequestDTO;
import com.danvinicius.ecommerce.dto.auth.AuthenticationResponseDTO;
import com.danvinicius.ecommerce.dto.user.UserRequestDTO;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthController authController;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe() {
        User user = userService.getMe();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> createUser(@Valid @RequestBody UserRequestDTO data) {
        this.userService.createUser(data);
        ResponseEntity<AuthenticationResponseDTO> response = authController
        .authenticate(new AuthenticationRequestDTO(data.email(), data.password()));
        return response;
    }

    @PostMapping("/admin")
    public ResponseEntity<User> createAdminUser(@Valid @RequestBody UserRequestDTO data) {
        User user = this.userService.createAdminUser(data);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserRequestDTO data) {
        User user = userService.updateUser(id, data);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable String id) {
        userService.deleteAdminUser(id);
        return ResponseEntity.noContent().build();
    }
}
