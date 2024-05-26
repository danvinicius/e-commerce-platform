package com.danvinicius.ecommerce.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.order.OrderRequestDTO;
import com.danvinicius.ecommerce.entities.address.ShippingAddress;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.entities.cart.CartItem;
import com.danvinicius.ecommerce.entities.order.Order;
import com.danvinicius.ecommerce.entities.order.OrderItem;
import com.danvinicius.ecommerce.entities.order.OrderStatus;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.entities.user.User;
import com.danvinicius.ecommerce.entities.user.UserRole;
import com.danvinicius.ecommerce.exceptions.BadRequestException;
import com.danvinicius.ecommerce.exceptions.ForbiddenException;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.OrderItemRepository;
import com.danvinicius.ecommerce.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressService shippingAddressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private User user() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userService.getUserById(user.getId().toString());
    }

    private Boolean userHasPermission(Order order) {
        return this.user().getRole().equals(UserRole.ADMIN.getRole()) || order.getUser().equals(this.user());
    }

    public Order getOrderById(String id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
        if (!userHasPermission(order)) {
            throw new ForbiddenException();
        }
        ;
        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequestDTO data) {
        Cart cart = cartService.getCartById(data.cartId());
        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }
        ShippingAddress address = null;
        if (data.shippingAddressId() != null && !data.shippingAddressId().isEmpty()) {
            address = shippingAddressService.getAddressById(data.shippingAddressId());
        }
        Order order = new Order();
        order.setUser(this.user());
        order.setInstant(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT.getStatus());
        order.setShippingAddress(address);
        orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {
            Product product = productService.getProductById(cartItem.getProduct().getId().toString());
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
            orderItemRepository.save(orderItem);
        }

        cartService.emptyCart(cart);

        return order;
    }

    public Order updateOrder(String id, OrderRequestDTO data) throws BadRequestException {
        Order order = getOrderById(id);
        if (!userHasPermission(order)) {
            throw new ForbiddenException();
        }
        ;
        if (data.shippingAddressId() != null && !data.shippingAddressId().isEmpty()) {
            if (order.getStatus() != OrderStatus.WAITING_PAYMENT.getStatus()) {
                throw new BadRequestException("Order shipping address can't be updated after payment");
            }
            ShippingAddress address = shippingAddressService.getAddressById(data.shippingAddressId());
            order.setShippingAddress(address);
        }
        orderRepository.save(order);
        return order;
    }

    public void cancelOrder(String id) {
        Order order = getOrderById(id);
        if (!userHasPermission(order)) {
            throw new ForbiddenException();
        }
        ;
        order.setStatus(OrderStatus.CANCELED.getStatus());
        orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        Order order = getOrderById(id);
        if (!userHasPermission(order)) {
            throw new ForbiddenException();
        }
        ;
        orderRepository.delete(order);
    }
}
