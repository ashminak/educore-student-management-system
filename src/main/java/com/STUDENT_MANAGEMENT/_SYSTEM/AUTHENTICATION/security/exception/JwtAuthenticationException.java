package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
