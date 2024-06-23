package com.danvinicius.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO (@NotBlank String name, Double discount) {
    
}
