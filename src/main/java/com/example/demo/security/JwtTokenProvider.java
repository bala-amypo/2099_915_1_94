package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtTokenProvider {

    private String jwtSecret;
    private final long jwtExpirationMs = 86400000; // 1 day

    // REQUIRED by Spring
    public JwtTokenProvider() {
        this.jwtSecret = "defaultSecretKeyForJwt123456789";
    }

    // REQUIRED by TestNG
    public JwtTokenProvider(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generateToken(Authentication authentication,
                                Long userId,
                                String role,
                                String email) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
