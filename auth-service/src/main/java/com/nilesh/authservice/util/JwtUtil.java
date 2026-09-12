package com.nilesh.authservice.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +1000 * 60 * 60)) // 1 hour expiration
                .signWith(key)
                .compact();
    }

    public void validateToken(String token){

        try {
            Jwts.parser().verifyWith((SecretKey) key)
                    .build()
                    .parseClaimsJws(token);
        }
        catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature", e);
        }
        catch (JwtException e) {
            throw new JwtException("Invalid JWT token", e);
        }
    }
}
