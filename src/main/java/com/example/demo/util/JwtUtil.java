package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public JwtUtil() {
    }

    public String generateToken(String email) {
        return "DUMMY_TOKEN";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String extractUsername(String token) {
        return "dummy@user.com";
    }
}
