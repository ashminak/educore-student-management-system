package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for publishing student marks")
public class MarksCreateRequest {

    @Schema(
            description = "Enrollment ID for which marks are being published",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Enrollment ID is required")
    private Long enrollmentId;

    @Schema(
            description = "Internal assessment marks",
            example = "18.5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Internal marks are required")
    @DecimalMin(value = "0", message = "Internal marks cannot be negative")
    @DecimalMax(value = "20", message = "Internal marks cannot exceed 20")
    private Double internalMarks;

    @Schema(
            description = "Practical examination marks",
            example = "17.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Practical marks are required")
    @DecimalMin(value = "0", message = "Practical marks cannot be negative")
    @DecimalMax(value = "20", message = "Practical marks cannot exceed 20")
    private Double practicalMarks;

    @Schema(
            description = "Final examination marks",
            example = "19.0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Final marks are required")
    @DecimalMin(value = "0", message = "Final marks cannot be negative")
    @DecimalMax(value = "20", message = "Final marks cannot exceed 20")
    private Double finalMarks;

    @Schema(
            description = "Total marks obtained",
            example = "54.5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Total marks are required")
    @DecimalMin(value = "0", message = "Total marks cannot be negative")
    @DecimalMax(value = "60", message = "Total marks cannot exceed 60")
    private Double totalMarks;

    @Schema(
            description = "Additional remarks about the student's performance",
            example = "Excellent performance in practical examination."
    )
    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;


}