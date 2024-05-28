package com.danvinicius.ecommerce.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO (
    @Email String to,
    @NotBlank String subject,
    @NotBlank String body) {
}