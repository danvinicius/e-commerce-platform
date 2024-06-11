package com.danvinicius.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.danvinicius.ecommerce.dto.auth.AuthenticationRequestDTO;
import com.danvinicius.ecommerce.dto.auth.AuthenticationResponseDTO;
import com.danvinicius.ecommerce.services.AuthenticationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@Valid @RequestBody AuthenticationRequestDTO data) {
        AuthenticationResponseDTO response = authenticationService.authenticate(data);
        return ResponseEntity.ok(response);
    }
}
