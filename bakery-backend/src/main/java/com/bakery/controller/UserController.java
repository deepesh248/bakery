package com.bakery.controller;

import com.bakery.dto.LoginRequest;
import com.bakery.model.Address;
import com.bakery.model.User;
import com.bakery.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setPhone(updatedUser.getPhone());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/addresses")
    public List<Address> getUserAddresses(@PathVariable Long id) {
        return userRepository.findById(id).map(User::getAddresses).orElse(List.of());
    }
    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest login, HttpSession session) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if (user != null) {
            session.setAttribute("userId", user.getId());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }
    


}
