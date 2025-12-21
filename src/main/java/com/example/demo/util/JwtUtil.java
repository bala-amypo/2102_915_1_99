package com.example.demo.util;

import java.util.Date;
import java.util.Set;

public class JwtUtil {

    public JwtUtil() {
    }

    public String generateToken(String username) {
        return "DUMMY_TOKEN_" + username;
    }

    public String generateToken(String username, Long userId, Set<String> roles) {
        return "DUMMY_TOKEN_" + username + "_" + userId;
    }

    public String extractUsername(String token) {
        return "dummy";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public Date extractExpiration(String token) {
        return new Date(System.currentTimeMillis() + 100000);
    }
}
