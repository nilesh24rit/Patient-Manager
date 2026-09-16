package com.nilesh.apigateway.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class JwtValidationException {
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public Mono<Void> handleWebClientResponseException(WebClientResponseException.Unauthorized e) {
        return Mono.error(new RuntimeException("JWT validation failed: " + e.getMessage()));
    }
}
