package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danvinicius.ecommerce.entities.address.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, UUID> {
    
}
