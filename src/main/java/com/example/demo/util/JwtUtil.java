package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secret = "mySecretKey";
    private final long expiration = 1000 * 60 * 60 * 24;

    public JwtUtil() {
    }

    public String generateToken(String email, Long userId, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("userId", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getClaims(String token) {
        return getAllClaimsFromToken(token);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Boolean validateToken(String token) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return !isTokenExpired(claims.getExpiration());
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateToken(String token, String username) {
        try {
            final String email = getEmailFromToken(token);
            return email.equals(username) && !isTokenExpired(getExpirationDateFromToken(token));
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }
}
