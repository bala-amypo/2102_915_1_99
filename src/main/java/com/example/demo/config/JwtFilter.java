// src/main/java/com/example/demo/config/JwtFilter.java
package com.example.demo.config;

import com.example.demo.service.impl.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                email = (String) jwtUtil.getClaims(token).get("email");
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails details = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        chain.doFilter(request, response);
    }
}
