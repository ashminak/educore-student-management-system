package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.advice;

import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.exception.InvalidJwtTokenException;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.exception.TokenExpiredException;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiErrorResponse> handleExpired(
            TokenExpiredException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(build(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalid(
            InvalidJwtTokenException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(build(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAll(
            Exception ex,
            HttpServletRequest request) {

        ex.printStackTrace();   // Very important while debugging

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(build(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),   // show actual error while debugging
                        request.getRequestURI()));
    }

    public ApiErrorResponse build(
            HttpStatus status,
            String message,
            String path){
        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
    }

}
