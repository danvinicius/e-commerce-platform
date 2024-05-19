package com.danvinicius.ecommerce.dto.address;

public record ShippingAddressRequestDTO(
        String address,
        String city,
        String state,
        String zipCode,
        String country,
        String complement) {
}
