package com.danvinicius.ecommerce.dto.auth;

public record AuthenticationResponseDTO (String token, String name, String email, String role) {
    
}
