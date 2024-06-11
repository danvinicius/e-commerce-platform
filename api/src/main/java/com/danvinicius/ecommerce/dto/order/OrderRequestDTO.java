package com.danvinicius.ecommerce.dto.order;

import jakarta.validation.constraints.NotBlank;

public record OrderRequestDTO(@NotBlank String cartId, String shippingAddressId) {
    
}
