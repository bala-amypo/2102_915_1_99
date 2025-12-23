package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "mysecretkeymysecretkeymysecretkey12345".getBytes()
    );

    private final long expirationMs = 24 * 60 * 60 * 1000;

    public String generateToken(String email, Long userId, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of(
                        "email", email,
                        "userId", userId,
                        "roles", roles
                ))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
