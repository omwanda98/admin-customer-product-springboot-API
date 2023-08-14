package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.dto.UserDTO;
import com.eshop.entity.User;
import com.eshop.services.UserService;

import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        // Validate credentials
        UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());
        if (userDetails == null || !passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        // Return successful login response
        return ResponseEntity.ok("User logged in successfully");
    }
}
