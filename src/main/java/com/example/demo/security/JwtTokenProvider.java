package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtTokenProvider {

    private final String secretKey;
    private final long validity = 24 * 60 * 60 * 1000; // 1 day

    public JwtTokenProvider(String secretKey) {
        this.secretKey = secretKey;
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
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
