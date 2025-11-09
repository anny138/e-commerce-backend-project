package com.ananya.ECommerce.repository;

import com.ananya.ECommerce.entity.Cart;
import com.ananya.ECommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
