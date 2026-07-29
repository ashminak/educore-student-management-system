package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.exception;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
