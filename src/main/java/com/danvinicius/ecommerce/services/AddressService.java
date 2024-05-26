package com.danvinicius.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.address.ShippingAddressRequestDTO;
import com.danvinicius.ecommerce.entities.address.ShippingAddress;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.entities.user.UserRole;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.exceptions.ForbiddenException;
import com.danvinicius.ecommerce.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    private User user() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userService.getUserById(user.getId().toString());
    }
    
    private Boolean userHasPermission(ShippingAddress address) {
        return this.user().getRole().equals(UserRole.ADMIN.getRole()) || address.getUser().equals(this.user());
    }

    public ShippingAddress getAddressById(String id) throws ResourceNotFoundException {
        ShippingAddress address = addressRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
        if (!userHasPermission(address)) {
            throw new ForbiddenException();
        };
        return address;
    }
    
    public List<ShippingAddress> getAllAddresss() {
        return addressRepository.findAll();
    }

    public ShippingAddress createAddress(ShippingAddressRequestDTO data) {
        ShippingAddress address = new ShippingAddress(data);
        address.setUser(this.user());
        addressRepository.save(address);
        return address;
    }

    public ShippingAddress updateAddress(String id, ShippingAddressRequestDTO data) {
        ShippingAddress address = getAddressById(id);
        if (!userHasPermission(address)) {
            throw new ForbiddenException();
        };
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
        if (!userHasPermission(address)) {
            throw new ForbiddenException();
        };
        addressRepository.delete(address);
    }
    
}
