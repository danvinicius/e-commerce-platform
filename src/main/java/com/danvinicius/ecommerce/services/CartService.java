package com.danvinicius.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.cart.CartItemRequestDTO;
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

    private Boolean isProductInCart(Cart cart, Product product) {
        return cart.getItems()
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                .findFirst()
                .isPresent();
    }

    private BigDecimal calculateCartItemCost(Product product, Integer quantity) {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Cart addItemToCart(String cartId, CartItemRequestDTO item) throws ProductAlreadyInCartException, ProductUnavailable {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(item.productId());

        if (isProductInCart(cart, product)) {
            throw new ProductAlreadyInCartException();
        }
        if (product.getQuantity() == 0 || product.getQuantity() < item.quantity()) {
            throw new ProductUnavailable("Item quantity not available");
        }
        
        // adds cart item to cart
        CartItem cartItem = new CartItem(null, cart, product, item.quantity(), product.getPrice());
        cart.getItems().add(cartItem);
        
        // updates cart total price and saves it
        cart.setTotalPrice(cart.getTotalPrice().add(calculateCartItemCost(product, item.quantity())));
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
        // removes cart item from cart
        cart.getItems().removeIf(cartItem -> cartItem.getProduct().getId().equals(product.getId()));
        
        // updates cart total price and saves it
        cart.setTotalPrice(cart.getTotalPrice().subtract(calculateCartItemCost(product, item.quantity())));
        cartRepository.save(cart);

        // increases product quantity at stock and saves it
        product.setQuantity(product.getQuantity() + item.quantity());
        productService.saveProduct(product);

        return cart;
    }

    public Cart increaseItemQuantityInCart(String cartId, CartItemRequestDTO item) throws ProductNotInCartException, ProductUnavailable {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(item.productId());
        if (!isProductInCart(cart, product)) {
            throw new ProductNotInCartException();
        }
        CartItem cartItem = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(product.getId())).findFirst().get();
        if (product.getQuantity() - (item.quantity() + cartItem.getQuantity()) < 0) {
            throw new ProductUnavailable("Item quantity not available");
        }
        cartItem.setQuantity(cartItem.getQuantity() + item.quantity());
        
        // updates cart total price and saves it
        cart.setTotalPrice(cart.getTotalPrice().add(calculateCartItemCost(product, item.quantity())));
        cartRepository.save(cart);

        // decreases product quantity at stock and saves it
        product.setQuantity(product.getQuantity() - item.quantity());
        productService.saveProduct(product);

        return cart;
    }

    public Cart decreaseItemQuantityInCart(String cartId, CartItemRequestDTO item) throws ProductNotInCartException {
        Cart cart = getCartById(cartId);
        Product product = productService.getProductById(item.productId());
        if (!isProductInCart(cart, product)) {
            throw new ProductNotInCartException();
        }
        CartItem cartItem = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(product.getId())).findFirst().get();
        cartItem.setQuantity(cartItem.getQuantity() - item.quantity());

        if (cartItem.getQuantity() <= 0) {
            removeItemFromCart(cartId, item);
        }
        
        // updates cart total price and saves it
        cart.setTotalPrice(cart.getTotalPrice().subtract(calculateCartItemCost(product, item.quantity())));
        cartRepository.save(cart);

        // increases product quantity at stock and saves it
        product.setQuantity(product.getQuantity() + item.quantity());
        productService.saveProduct(product);

        return cart;
    }

    public void deleteCart(String id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
    }
}
