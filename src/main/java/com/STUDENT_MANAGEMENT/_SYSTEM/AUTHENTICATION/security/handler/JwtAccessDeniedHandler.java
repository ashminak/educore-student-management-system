package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.handler;

import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.response.ApiErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper =
            new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("Access denied : {}",
                request.getRequestURI());
        ApiErrorResponse error=
                ApiErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN.value())
                        .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                        .message("You don't have permission to access this resources")
                        .path(request.getRequestURI())
                        .build();
        response.setStatus(
                HttpStatus.FORBIDDEN.value());
        response.setContentType(
                MediaType.APPLICATION_JSON_VALUE
        );
        objectMapper.writeValue(
                response.getOutputStream(),
                error
        );
    }
}
