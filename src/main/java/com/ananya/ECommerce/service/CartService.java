package com.ananya.ECommerce.service;

import com.ananya.ECommerce.entity.Cart;
import com.ananya.ECommerce.entity.CartItem;
import com.ananya.ECommerce.entity.Product;
import com.ananya.ECommerce.entity.User;
import com.ananya.ECommerce.exception.ResourceNotFoundException;
import com.ananya.ECommerce.repository.CartItemRepository;
import com.ananya.ECommerce.repository.CartRepository;
import com.ananya.ECommerce.repository.ProductRepository;
import com.ananya.ECommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Dummy current user (replace with actual authenticated user later)
    private User getCurrentUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // ✅ Add product to cart
    public Cart addToCart(Long productId, int quantity) {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> new Cart(user));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(cart, product, quantity);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    // ✅ Update product quantity in cart
    public Cart updateCart(Long productId, int quantity) {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart"));

        item.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    // ✅ Remove product from cart
    public Cart removeFromCart(Long productId) {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }

    // ✅ Get user’s cart
    public Cart getUserCart() {
        User user = getCurrentUser();
        return cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }
}
