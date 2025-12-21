package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        String token = jwtUtil.generateToken(
                request.getEmail(),
                1L,
                Set.of("USER")
        );
        return new AuthResponse(token);
    }
}
