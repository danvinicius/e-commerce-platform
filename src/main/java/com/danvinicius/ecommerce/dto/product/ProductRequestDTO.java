package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;

public record ProductRequestDTO(
    String name,
    String description,
    String imageUrl,
    BigDecimal price,
    String categoryId
    ) {
}
