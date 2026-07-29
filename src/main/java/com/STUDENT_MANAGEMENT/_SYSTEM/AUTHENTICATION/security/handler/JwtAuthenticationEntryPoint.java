package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.handler;

import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.response.ApiErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
            log.warn("Unauthorized access to {}",
                    request.getRequestURI());
        ApiErrorResponse error =
                ApiErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .message("Authentication Required")
                        .path(request.getRequestURI())
                        .build();

        response.setStatus(
                HttpStatus.UNAUTHORIZED.value());
        response.setContentType(
                MediaType.APPLICATION_JSON_VALUE
        );
        objectMapper.writeValue(
                response.getOutputStream(),
                error
        );

    }
}
