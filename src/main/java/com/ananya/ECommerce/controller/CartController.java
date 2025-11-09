package com.ananya.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.ECommerce.entity.Cart;
import com.ananya.ECommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	public CartService cartService;
	@PostMapping("/add/{productId}")
	public ResponseEntity<Cart> addToCart(@PathVariable Long productId,@RequestParam int quantity){
		return ResponseEntity.ok(cartService.addToCart(productId,quantity));
	}
	@PostMapping("/update/{productId}")
	public ResponseEntity<Cart> updateCart(@PathVariable Long productId,@RequestParam int quantity){
		return ResponseEntity.ok(cartService.updateCart(productId,quantity));
	}
	@PostMapping("/remove/{productId}")
	public ResponseEntity<Cart> removeFromCart(@PathVariable Long productId){
		return ResponseEntity.ok(cartService.removeFromCart(productId));
	}
	@GetMapping
	public ResponseEntity<Cart> getCart(){
		return ResponseEntity.ok(cartService.getUserCart());
	}
}
