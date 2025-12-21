package com.example.demo.util;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JwtUtil {

    public String generateToken(String email, Long userId, Set<String> roles) {
        return "dummy-token";
    }
}
