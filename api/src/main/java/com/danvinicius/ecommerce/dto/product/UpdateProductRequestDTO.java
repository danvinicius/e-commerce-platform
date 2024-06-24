package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequestDTO(
    @NotBlank String name,
    @NotBlank String description,
    String imageUrl,
    @NotNull BigDecimal price,
    Boolean recommendedOnMainPage,
     Map<String, Integer> stock,
    Double weight,
    Double discount
) {
    
}
