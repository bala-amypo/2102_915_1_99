package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        User user = userRepository.findByEmail(req.get("email")).orElseThrow();

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getId(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
