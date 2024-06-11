package com.danvinicius.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.cart.CartItemRequestDTO;
import com.danvinicius.ecommerce.dto.cart.CartRequestDTO;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.entities.cart.CartItem;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.entities.user.UserRole;
import com.danvinicius.ecommerce.exceptions.ForbiddenException;
import com.danvinicius.ecommerce.exceptions.ProductAlreadyInCartException;
import com.danvinicius.ecommerce.exceptions.ProductNotInCartException;
import com.danvinicius.ecommerce.exceptions.ProductUnavailableException;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.CartItemRepository;
import com.danvinicius.ecommerce.repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private User user() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userService.getUserById(user.getId().toString());
    }
    
    private Boolean userHasPermission(Cart cart) {
        return this.user().getRole().equals(UserRole.ADMIN.getRole()) || cart.getUser().equals(this.user());
    }

    public Cart getCartById(String id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
        if (!userHasPermission(cart)) {
            throw new ForbiddenException();
        };
        return cart;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart emptyCart(Cart cart) {
        for (CartItem cartItem : cart.getItems()) {
            cartItemRepository.delete(cartItem);
        }
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        return cart;
    }

    public Cart createCart(CartRequestDTO data) {
        Cart cart = new Cart();
        cart.setUser(this.user());
        data.items().stream().forEach(item -> {
            Product product = productService.getProductById(item.productId());
            CartItem cartItem = new CartItem(null, cart, product, item.quantity(), product.getPrice());
            if (product.getQuantity() == 0 || item.quantity() > product.getQuantity()) {
                throw new ProductUnavailableException("Item quantity not available");
            }
            cart.getItems().add(cartItem);
            product.setQuantity(product.getQuantity() - item.quantity());
            productService.saveProduct(product);
        });
        updateCartTotalPrice(cart);
        cartRepository.save(cart);
        return cart;
    }
    
    public Cart addItemToCart(String cartId, CartItemRequestDTO item) throws ProductAlreadyInCartException, ProductUnavailableException {
        Cart cart = getCartById(cartId);

        if (!userHasPermission(cart)) {
            throw new ForbiddenException();
        };

        Product product = productService.getProductById(item.productId());

        if (product.getQuantity() == 0 || item.quantity() > product.getQuantity()) {
            throw new ProductUnavailableException("Item quantity not available");
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

        if (!userHasPermission(cart)) {
            throw new ForbiddenException();
        };

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
        if (!userHasPermission(cart)) {
            throw new ForbiddenException();
        };
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
