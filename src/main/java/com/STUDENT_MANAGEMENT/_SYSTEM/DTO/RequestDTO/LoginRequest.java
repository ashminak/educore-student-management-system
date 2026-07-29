package com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for user login")
public class LoginRequest {

    @Schema(
            description = "Username or registered email address",
            example = "ashmina123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Username or email is required")
    @Size(
            max = 100,
            message = "Username or email cannot exceed 100 characters"
    )
    private String username;

    @Schema(
            description = "User password",
            example = "Password@123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 100,
            message = "Password must be between 8 and 100 characters"
    )
    private String password;
}