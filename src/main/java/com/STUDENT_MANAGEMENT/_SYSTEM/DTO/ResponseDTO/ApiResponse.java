package com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {

    @Schema(
            description = "Indicates whether the request was successful",
            example = "true"
    )
    private Boolean success;

    @Schema(
            description = "Response message",
            example = "User created successfully"
    )
    private String message;

    @Schema(
            description = "Response payload"
    )
    private T data;

    @Schema(
            description = "Time when the response was generated",
            example = "2026-07-13T20:15:30"
    )
    private LocalDateTime timestamp;
}