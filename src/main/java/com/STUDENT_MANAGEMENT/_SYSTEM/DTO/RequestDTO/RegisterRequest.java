package com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO;

import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object for registering a new user")
public class RegisterRequest {

    @Schema(
            description = "Full name of the user",
            example = "Ashmina Khatun",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Full name is required")
    @Size(
            min = 3,
            max = 100,
            message = "Full name must be between 3 and 100 characters"
    )
    private String fullName;

    @Schema(
            description = "User's email address",
            example = "ashmina@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Size(
            max = 100,
            message = "Email cannot exceed 100 characters"
    )
    private String email;

    @Schema(
            description = "Unique username",
            example = "ashmina123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Username is required")
    @Size(
            min = 4,
            max = 100,
            message = "Username must be between 4 and 100 characters"
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

    @Schema(
            description = "Role assigned to the user",
            example = "STUDENT",
            allowableValues = {
                    "ADMIN",
                    "TEACHER",
                    "STUDENT"
            },
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Role role;
}