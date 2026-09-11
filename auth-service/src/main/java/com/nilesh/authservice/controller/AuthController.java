package com.nilesh.authservice.controller;

import com.nilesh.authservice.dto.LoginRequestDto;
import com.nilesh.authservice.dto.LoginResponseDto;
import com.nilesh.authservice.service.AuthService;
import com.nilesh.authservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    private final AuthService authService;
    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.authService = authService;
    }
    @Operation(summary = "Login user", description = "Generate JWT token for user authentication")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<String> tokenOptional = authService.authenticate(loginRequestDto);
        if (tokenOptional.isPresent()) {
            LoginResponseDto responseDto = new LoginResponseDto(tokenOptional.get());
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
