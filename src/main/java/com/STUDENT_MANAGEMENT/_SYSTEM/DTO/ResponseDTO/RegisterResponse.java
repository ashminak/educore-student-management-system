package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response returned after successful user registration")
public class RegisterResponse {

    @Schema(
            description = "Unique ID of the newly registered user",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Registered email address",
            example = "ashmina@gmail.com"
    )
    private String email;

    @Schema(
            description = "Registered username",
            example = "ashmina123"
    )
    private String username;

    @Schema(
            description = "Registration status message",
            example = "User registered successfully."
    )
    private String message;
}