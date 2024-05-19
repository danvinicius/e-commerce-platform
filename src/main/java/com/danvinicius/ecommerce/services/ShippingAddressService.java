package com.danvinicius.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.address.ShippingAddressRequestDTO;
import com.danvinicius.ecommerce.entities.address.ShippingAddress;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.ShippingAddressRepository;

@Service
public class ShippingAddressService {
    @Autowired
    private ShippingAddressRepository addressRepository;

    public ShippingAddress getAddressById(String id) throws ResourceNotFoundException {
        return addressRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }
    
    public List<ShippingAddress> getAllAddresss() {
        return addressRepository.findAll();
    }

    public ShippingAddress createAddress(ShippingAddressRequestDTO data) {
        ShippingAddress address = new ShippingAddress(data);
        addressRepository.save(address);
        return address;
    }

    public ShippingAddress updateAddress(String id, ShippingAddressRequestDTO data) {
        ShippingAddress address = getAddressById(id);
        if (data.address() != null && !data.address().isEmpty()) {
            address.setAddress(data.address());
        }
        if (data.complement() != null && !data.complement().isEmpty()) {
            address.setComplement(data.complement());
        }
        addressRepository.save(address);
        return address;
    }

    public void deleteAddress(String id) {
        ShippingAddress address = getAddressById(id);
        addressRepository.delete(address);
    }
    
}
