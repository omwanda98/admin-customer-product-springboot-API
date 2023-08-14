package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.dto.UserDTO;
import com.eshop.entity.User;
import com.eshop.services.UserService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok("User added successfully");
    }

//    @PutMapping("/edit-user/{userId}")
//    public ResponseEntity<?> editUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
//        // Implement user editing logic here
//        User editedUser = userService.editUser(userId, userDTO);
//        return ResponseEntity.ok("User edited successfully");
//    }
}
