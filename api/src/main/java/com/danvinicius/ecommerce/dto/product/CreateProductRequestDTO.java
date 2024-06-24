package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequestDTO(
    @NotBlank String name,
    @NotBlank String description,
    String imageUrl,
    @NotNull BigDecimal price,
    Set<String> categoriesIds,
    Map<String, Integer> stock,
    Boolean recommendedOnMainPage,
    BigDecimal weight,
    Double discount
    ) {
}
