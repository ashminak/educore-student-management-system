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
@Schema(description = "Request object for changing the user's password")
public class ChangePasswordRequest {

    @Schema(
            description = "Current password of the user",
            example = "OldPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @Schema(
            description = "New password (minimum 8 characters)",
            example = "NewPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "New password is required")
    @Size(
            min = 8,
            message = "Password must be at least 8 characters long"
    )
    private String newPassword;

    @Schema(
            description = "Confirm the new password",
            example = "NewPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}