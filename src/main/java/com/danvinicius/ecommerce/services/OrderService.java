package com.danvinicius.ecommerce.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.order.OrderRequestDTO;
import com.danvinicius.ecommerce.entities.address.ShippingAddress;
import com.danvinicius.ecommerce.entities.cart.Cart;
import com.danvinicius.ecommerce.entities.cart.CartItem;
import com.danvinicius.ecommerce.entities.order.Order;
import com.danvinicius.ecommerce.entities.order.OrderItem;
import com.danvinicius.ecommerce.entities.order.OrderStatus;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.exceptions.BadRequestException;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public Order getOrderById(String id) throws ResourceNotFoundException {
        return orderRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(OrderRequestDTO data) {
        ShippingAddress address = shippingAddressService.getAddressById(data.shippingAddressId());
        Cart cart = cartService.getCartById(data.cartId());
        Order order = new Order(null, null, Instant.parse(LocalDateTime.now().toString()), OrderStatus.WAITING_PAYMENT, address);

        for (CartItem cartItem: cart.getItems()) {
            Product product = productService.getProductById(cartItem.getProduct().getId().toString());
            OrderItem orderItem = new OrderItem(null, cartItem.getQuantity(), cartItem.getPrice());
            orderItem.getId().setOrder(order);
            orderItem.getId().setProduct(product);
            order.getItems().add(orderItem);
        }

        orderRepository.save(order);
        return order;
    }

    public Order updateOrder(String id, OrderRequestDTO data) throws BadRequestException {
        Order order = getOrderById(id);
        if (data.shippingAddressId() != null && !data.shippingAddressId().isEmpty()) {
            if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
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
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }
}
