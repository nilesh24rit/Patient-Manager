package com.nilesh.authservice.service;

import com.nilesh.authservice.dto.LoginRequestDto;
import com.nilesh.authservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        Optional<String>token=userService.findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(LoginRequestDto.getPassword(),
                        u.getPassword()))
                .map(u-> jwtUtil.generateToken(u.getEmail(), u.getRole()));
        return token;
    }
}
