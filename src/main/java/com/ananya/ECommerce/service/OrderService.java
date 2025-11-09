package com.ananya.ECommerce.service;

import com.ananya.ECommerce.dto.OrderDTO;
import com.ananya.ECommerce.entity.*;
import com.ananya.ECommerce.exception.ResourceNotFoundException;
import com.ananya.ECommerce.repository.CartRepository;
import com.ananya.ECommerce.repository.OrderRepository;
import com.ananya.ECommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Dummy current user (for testing — replace later with JWT user)
    private User getCurrentUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // ✅ Place order from current cart
    public Order placeOrder(String paymentMode) {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot place order.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentMode(paymentMode);
        order.setStatus("PENDING");
        order.setTotalAmount(cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum());

        order.setOrderItems(
                cart.getItems().stream()
                        .map(item -> new OrderItem(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice()))
                        .collect(Collectors.toList())
        );

        // Clear cart after placing order
        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    // ✅ Get all orders for user
    public List<OrderDTO> getUserOrders() {
        User user = getCurrentUser();
        return orderRepository.findByUser(user)
                .stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ✅ Get specific order by ID
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        return OrderDTO.fromEntity(order);
    }

    // ✅ Update order status (e.g., ADMIN updates it)
    public void updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
