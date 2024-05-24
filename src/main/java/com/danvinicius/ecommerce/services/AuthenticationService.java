package com.danvinicius.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.config.security.TokenService;
import com.danvinicius.ecommerce.dto.auth.AuthenticationRequestDTO;
import com.danvinicius.ecommerce.dto.auth.AuthenticationResponseDTO;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.repositories.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new AuthenticationResponseDTO(token);
    }
}
