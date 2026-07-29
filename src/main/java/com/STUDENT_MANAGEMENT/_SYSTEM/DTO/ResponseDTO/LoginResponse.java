package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response returned after successful user authentication")
public class LoginResponse {

    @Schema(
            description = "JWT access token used to access secured APIs",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.signature"
    )
    private String accessToken;

    @Schema(
            description = "JWT refresh token used to generate a new access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.refreshSignature"
    )
    private String refreshToken;

    @Schema(
            description = "Authentication token type",
            example = "Bearer"
    )
    private String tokenType;

    @Schema(
            description = "Access token expiration time in seconds",
            example = "3600"
    )
    private Long expiresIn;

    @Schema(
            description = "Authenticated user details"
    )
    private UserResponse user;
}