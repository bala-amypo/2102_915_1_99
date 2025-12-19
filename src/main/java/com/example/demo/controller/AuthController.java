package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", "dummy-token");
        response.put("userId", 1L);
        response.put("email", request.get("email"));
        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1L);
        response.put("email", request.get("email"));
        response.put("message", "registered");
        return response;
    }
}
