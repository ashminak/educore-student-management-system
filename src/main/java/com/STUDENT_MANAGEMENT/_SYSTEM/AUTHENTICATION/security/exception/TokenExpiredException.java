package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}
