package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for updating an enrollment")
public class EnrollmentUpdateRequest {

    @Schema(
            description = "Updated enrollment status",
            example = "COMPLETED",
            allowableValues = {
                    "ENROLLED",
                    "COMPLETED",
                    "DROP"
            }
    )
    private EnrollmentStatus status;
}
