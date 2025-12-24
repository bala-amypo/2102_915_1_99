// src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> body) {
        User u = new User();
        u.setName(body.getOrDefault("name", "User"));
        u.setEmail(body.get("email"));
        u.setPassword(body.getOrDefault("password", "password"));
        User saved = userService.registerUser(u);
        Map<String, Object> resp = new HashMap<>();
        resp.put("id", saved.getId());
        resp.put("email", saved.getEmail());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        User user = userService.findByEmail(req.getEmail());
        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        Set<String> roles = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet());
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), roles));
    }
}
