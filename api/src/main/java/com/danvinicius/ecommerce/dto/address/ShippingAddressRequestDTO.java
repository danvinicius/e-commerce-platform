package com.danvinicius.ecommerce.dto.address;

import jakarta.validation.constraints.NotBlank;

public record ShippingAddressRequestDTO(
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode,
        @NotBlank String country,
        String complement) {
}
