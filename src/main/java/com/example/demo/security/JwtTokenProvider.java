package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component   // ⭐ THIS IS THE FIX
public class JwtTokenProvider {

    private final String jwtSecret;
    private final long jwtExpirationMs = 86400000; // 1 day

    // ✅ Spring Boot runtime constructor
    public JwtTokenProvider(
            @Value("${app.jwt.secret:VerySecretKeyForJwtDemo1234567890}")
            String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    // ✅ Test-only constructor (DO NOT REMOVE)
    public JwtTokenProvider() {
        this.jwtSecret = "VerySecretKeyForJwtDemo1234567890";
    }

    public String generateToken(Authentication authentication,
                                Long userId,
                                String role,
                                String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
