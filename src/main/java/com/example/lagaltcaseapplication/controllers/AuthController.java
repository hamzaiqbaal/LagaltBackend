package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User inputUser) {
        Optional<User> optionalUser = userRepository.findByUsername(inputUser.getUsername());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(inputUser.getPassword())) {
                return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
    }
}