package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> request) {

        Map<String, Object> response = new HashMap<>();
        response.put("token", "dummy-token");
        response.put("type", "Bearer");
        response.put("userId", 1);
        response.put("username", request.getOrDefault("username", "test"));
        response.put("email", request.getOrDefault("email", "test@mail.com"));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> request) {

        Map<String, Object> response = new HashMap<>();
        response.put("id", 1);
        response.put("username", request.getOrDefault("username", "test"));
        response.put("email", request.getOrDefault("email", "test@mail.com"));
        response.put("message", "User registered successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
