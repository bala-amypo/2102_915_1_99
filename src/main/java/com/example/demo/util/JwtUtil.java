// src/main/java/com/example/demo/util/JwtUtil.java
package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    // Simple static secret to avoid relying on external config in tests
    private final Key key = Keys.hmacShaKeyFor("very-strong-secret-key-for-tests-0123456789".getBytes(StandardCharsets.UTF_8));
    private final long expirationMillis = 1000L * 60 * 60; // 1 hour

    public String generateToken(String email, Long userId, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        claims.put("roles", roles != null ? roles : Collections.emptySet());

        long now = System.currentTimeMillis();
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(now + expirationMillis))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            getAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Map<String, Object> getClaims(String token) {
        Claims c = getAllClaims(token);
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> e : c.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        // also include subject if needed
        map.putIfAbsent("email", c.get("email") != null ? c.get("email") : c.getSubject());
        return map;
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
