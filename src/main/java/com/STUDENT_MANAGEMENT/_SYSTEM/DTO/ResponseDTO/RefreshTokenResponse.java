package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response returned after successfully refreshing the access token")
public class RefreshTokenResponse {

    @Schema(
            description = "New JWT access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.signature"
    )
    private String accessToken;

    @Schema(
            description = "Refresh token used for future access token generation",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2htaW5hMTIzIiwiZXhwIjoxNzUzMDAwMDAwfQ.refreshSignature"
    )
    private String refreshToken;

    @Schema(
            description = "Authentication token type",
            example = "Bearer"
    )
    private String tokenType;
}