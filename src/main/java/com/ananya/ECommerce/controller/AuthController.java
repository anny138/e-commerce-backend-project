package com.ananya.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ananya.ECommerce.dto.AuthRequest;
import com.ananya.ECommerce.dto.AuthResponse;
import com.ananya.ECommerce.entity.User;
import com.ananya.ECommerce.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse("Login successful", user.getEmail(), user.getRole()));
    }
}
