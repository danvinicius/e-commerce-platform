package com.danvinicius.ecommerce.entities.address;

import java.io.Serializable;
import java.util.UUID;

import com.danvinicius.ecommerce.dto.address.ShippingAddressRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_address")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ShippingAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private String country;

    private String complement;

    public ShippingAddress(ShippingAddressRequestDTO data) {
        this.address = data.address();
        this.city = data.city();
        this.state = data.state();
        this.zipCode = data.zipCode();
        this.country = data.country();
        this.complement = data.complement();
    }
    
}
