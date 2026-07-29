package com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for generating a new access token using a refresh token")
public class RefreshTokenRequest {

    @Schema(
            description = "Valid JWT refresh token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.dummyRefreshTokenSignature",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}