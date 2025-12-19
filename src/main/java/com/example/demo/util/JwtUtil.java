// package com.example.demo.util;

// import org.springframework.stereotype.Component;

// import java.util.Map;
// import java.util.HashMap;
// import java.util.Set;

// @Component
// public class JwtUtil {

//     public String generateToken(String email, Long userId, Set<String> roles) {
//         return "dummy-token";
//     }

//     public boolean validateToken(String token) {
//         return true;
//     }

//     public Map<String, Object> getClaims(String token) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("email", "test@example.com");
//         claims.put("userId", 1L);
//         claims.put("roles", Set.of("ADMIN"));
//         return claims;
//     }
// }
