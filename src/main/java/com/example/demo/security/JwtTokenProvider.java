package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // --------------------------------------------------
    // REQUIRED BY TESTS (expiration config)
    // --------------------------------------------------
    @Value("${jwt.expiration:3600000}")
    private long jwtExpiration;

    // --------------------------------------------------
    // SECRET KEY (must be >= 256 bits)
    // --------------------------------------------------
    private SecretKey secretKey;

    // --------------------------------------------------
    // ✅ REQUIRED NO-ARG CONSTRUCTOR (Spring)
    // --------------------------------------------------
    public JwtTokenProvider() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // --------------------------------------------------
    // ✅ REQUIRED STRING CONSTRUCTOR (TestNG)
    // --------------------------------------------------
    public JwtTokenProvider(String secret) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // --------------------------------------------------
    // ✅ EXACT METHOD SIGNATURE EXPECTED BY TESTS
    // --------------------------------------------------
    public String generateToken(Authentication authentication,
                                Long userId,
                                String role,
                                String email) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId", userId)
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // --------------------------------------------------
    // ✅ REQUIRED BY testSecurity_JwtExpirationConfig
    // --------------------------------------------------
    public long getJwtExpiration() {
        return jwtExpiration;
    }
}
