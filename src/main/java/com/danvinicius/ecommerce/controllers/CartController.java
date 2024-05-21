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

import com.danvinicius.ecommerce.dto.cart.CartItemRequestDTO;
import com.danvinicius.ecommerce.dto.cart.CartRequestDTO;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.services.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable String id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok().body(carts);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@Valid @RequestBody CartRequestDTO data) {
        Cart cart = cartService.createCart(data);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cart.getId()).toUri();
        return ResponseEntity.created(uri).body(cart);
    }
    
    @PutMapping("/{id}/item/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable String id, @Valid @RequestBody CartItemRequestDTO data) {
        Cart cart = cartService.addItemToCart(id, data);
        return ResponseEntity.ok().body(cart);
    }

    @PutMapping("/{id}/item/remove")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String id, @Valid @RequestBody CartItemRequestDTO data) {
        Cart cart = cartService.removeItemFromCart(id, data);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
