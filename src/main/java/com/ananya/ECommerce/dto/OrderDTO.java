package com.ananya.ECommerce.dto;

import com.ananya.ECommerce.entity.Order;
import com.ananya.ECommerce.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {
    private Long id;
    private String status;
    private String paymentMode;
    private double totalAmount;
    private LocalDateTime orderDate;
    private List<String> products;

    public static OrderDTO fromEntity(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.id = order.getId();
        dto.status = order.getStatus();
        dto.paymentMode = order.getPaymentMode();
        dto.totalAmount = order.getTotalAmount();
        dto.orderDate = order.getOrderDate();
        dto.products = order.getOrderItems()
                .stream()
                .map(item -> item.getProduct().getName() + " (x" + item.getQuantity() + ")")
                .collect(Collectors.toList());
        return dto;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public List<String> getProducts() { return products; }
    public void setProducts(List<String> products) { this.products = products; }
}
