package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Authenticated user details")
public class UserResponse {

    @Schema(
            description = "Unique user ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Full name of the user",
            example = "Ashmina Khatun"
    )
    private String fullName;

    @Schema(
            description = "Registered email address",
            example = "ashmina@gmail.com"
    )
    private String email;

    @Schema(
            description = "Username used for login",
            example = "ashmina123"
    )
    private String username;

    @Schema(
            description = "Role assigned to the user",
            example = "STUDENT",
            allowableValues = {
                    "ADMIN",
                    "TEACHER",
                    "STUDENT"
            }
    )
    private Role role;
}