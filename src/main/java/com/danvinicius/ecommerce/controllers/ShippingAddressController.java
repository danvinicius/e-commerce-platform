package com.danvinicius.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.danvinicius.ecommerce.dto.address.ShippingAddressRequestDTO;
import com.danvinicius.ecommerce.entities.address.ShippingAddress;
import com.danvinicius.ecommerce.services.ShippingAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<ShippingAddress> getAddressById(@PathVariable String id) {
        ShippingAddress address = addressService.getAddressById(id);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping
    public ResponseEntity<List<ShippingAddress>> getAllAddresss() {
        List<ShippingAddress> addresses = addressService.getAllAddresss();
        return ResponseEntity.ok().body(addresses);
    }

    @PostMapping
    public ResponseEntity<ShippingAddress> createAddress(@Valid @RequestBody ShippingAddressRequestDTO data) {
        ShippingAddress address = addressService.createAddress(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ShippingAddress> updateAddress(@PathVariable String id, @RequestBody ShippingAddressRequestDTO data) {
        ShippingAddress address = addressService.updateAddress(id, data);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
