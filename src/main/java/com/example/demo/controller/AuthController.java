package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil = new JwtUtil();

    public AuthController() {
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = jwtUtil.generateToken(
                request.getUsername(),
                1L,
                Set.of("USER")
        );
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return "User registered";
    }
}
