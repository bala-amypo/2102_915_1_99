package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email, Long userId, Set<String> roles) {
        return "dummy-token";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String extractUsername(String token) {
        return "dummy@email.com";
    }

    public Map<String, Object> getClaims(String token) {
        return new HashMap<>();
    }
}
