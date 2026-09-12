package com.nilesh.authservice.service;

import com.nilesh.authservice.dto.LoginRequestDto;
import com.nilesh.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    //passwordEncoder.matches(loginRequestDto.getPassword(), u.getPassword())
    // checks if the provided password matches the hashed password stored in
    // the database for the user
    // if matched it generates a JWT token using the user's email and role
    // and returns it as an Optional<String>
    // if the user is not found or the password does not match
    // it returns an empty Optional
    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        Optional<String>token=userService.findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDto.getPassword(),
                        u.getPassword()))
                .map(u-> jwtUtil.generateToken(u.getEmail(), u.getRole()));
        return token;
    }

    public boolean validateToken(String token){
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
