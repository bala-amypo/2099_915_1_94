package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component   // ⭐ THIS IS THE FIX
public class JwtTokenProvider {

    private final String jwtSecret = "VerySecretKeyForJwtDemo1234567890";
    private final long jwtExpirationMs = 86400000; // 1 day

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
