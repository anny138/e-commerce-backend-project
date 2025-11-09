package com.ananya.ECommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.ECommerce.dto.OrderDTO;
import com.ananya.ECommerce.entity.Order;
import com.ananya.ECommerce.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping("/checkout")
	public ResponseEntity<Order> placeOrder(@RequestParam String paymentMode){
		return ResponseEntity.ok(orderService.placeOrder(paymentMode));
	}
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getOrders(){
		return ResponseEntity.ok(orderService.getUserOrders());
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
	return ResponseEntity.ok(orderService.getOrderById(id));
	}
	@PostMapping("/{id}/status")
	public ResponseEntity<String> updateStatus(@PathVariable Long id,@RequestParam String status){
		orderService.updateOrderStatus(id,status);
		return ResponseEntity.ok("Order Status Updated");
	}

}
