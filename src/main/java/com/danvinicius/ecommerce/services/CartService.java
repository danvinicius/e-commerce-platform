package com.danvinicius.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.cart.CartItemRequestDTO;
import com.danvinicius.ecommerce.dto.cart.CartRequestDTO;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.entities.cart.CartItem;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.exceptions.ProductAlreadyInCartException;
import com.danvinicius.ecommerce.exceptions.ProductNotInCartException;
import com.danvinicius.ecommerce.exceptions.ProductUnavailable;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public Cart getCartById(String id) throws ResourceNotFoundException {
        return cartRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart createCart(CartRequestDTO data) {
        Cart cart = new Cart();
        data.items().stream().forEach(item -> {
            Product product = productService.getProductById(item.productId());
            CartItem cartItem = new CartItem(null, cart, product, item.quantity(), product.getPrice());
            if (product.getQuantity() == 0 || item.quantity() > product.getQuantity()) {
                throw new ProductUnavailable("Item quantity not available");
            }
            cart.getItems().add(cartItem);
            product.setQuantity(product.getQuantity() - item.quantity());
            productService.saveProduct(product);
        });
        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        return cart;
    }
    
    public Cart addItemToCart(String cartId, CartItemRequestDTO item) throws ProductAlreadyInCartException, ProductUnavailable {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(item.productId());

        if (product.getQuantity() == 0 || item.quantity() > product.getQuantity()) {
            throw new ProductUnavailable("Item quantity not available");
        }

        if (isProductInCart(cart, product)) {
            CartItem cartItem = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(product.getId())).findFirst().get();
            cartItem.setQuantity(cartItem.getQuantity() + item.quantity());
        } else {
            // adds cart item to cart
            CartItem cartItem = new CartItem(null, cart, product, item.quantity(), product.getPrice());
            cart.getItems().add(cartItem);
        }
        
        // updates cart total price and saves it
        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        
        // decreases product quantity at stock and saves it
        product.setQuantity(product.getQuantity() - item.quantity());
        productService.saveProduct(product);
        
        return cart;
    }
    
    public Cart removeItemFromCart(String cartId, CartItemRequestDTO item) throws ProductNotInCartException {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(item.productId());
        if (!isProductInCart(cart, product)) {
            throw new ProductNotInCartException();
        }
        
        CartItem cartItem = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(product.getId())).findFirst().get();

        if (item.quantity() > cartItem.getQuantity()) {
            product.setQuantity(product.getQuantity() + cartItem.getQuantity());
            cart.getItems().remove(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - item.quantity());
            product.setQuantity(product.getQuantity() + item.quantity());
        }
        
        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        productService.saveProduct(product);
        
        return cart;
    }
    
    public void deleteCart(String id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
    }

    private void updateCartTotalPrice(Cart cart) {
        for (CartItem item: cart.getItems()) {
            cart.setTotalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
    }

    private Boolean isProductInCart(Cart cart, Product product) {
        return cart.getItems()
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                .findFirst()
                .isPresent();
    }
}
