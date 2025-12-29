
package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {

    // Static secret for tests
    private final Key key = Keys.hmacShaKeyFor(
        "very-strong-secret-key-for-tests-0123456789".getBytes(StandardCharsets.UTF_8)
    );
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

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        Object id = getAllClaims(token).get("userId");
        return id != null ? Long.valueOf(id.toString()) : null;
    }

    @SuppressWarnings("unchecked")
    public Set<String> extractRoles(String token) {
        Object roles = getAllClaims(token).get("roles");
        if (roles instanceof Collection<?>) {
            Set<String> result = new HashSet<>();
            for (Object r : (Collection<?>) roles) {
                result.add(r.toString());
            }
            return result;
        }
        return Collections.emptySet();
    }

    public Map<String, Object> getClaims(String token) {
        Claims c = getAllClaims(token);
        Map<String, Object> map = new HashMap<>(c);
        map.putIfAbsent("email", c.get("email") != null ? c.get("email") : c.getSubject());
        return map;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                   .parseClaimsJws(token).getBody();
    }
}
