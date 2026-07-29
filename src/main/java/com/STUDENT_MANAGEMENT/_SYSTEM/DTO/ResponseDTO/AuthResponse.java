package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Authentication response containing the JWT token and user information")
public class AuthResponse {

    @Schema(
            description = "JWT access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.signature"
    )
    private String token;

    @Schema(
            description = "Authenticated user's username",
            example = "ashmina123"
    )
    private String username;

    @Schema(
            description = "Role assigned to the authenticated user",
            example = "ADMIN"
    )
    private String role;
}