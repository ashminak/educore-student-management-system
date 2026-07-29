package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for updating student marks")
public class MarksUpdateRequest {

    @Schema(
            description = "Updated internal assessment marks",
            example = "18.5"
    )
    @DecimalMin(value = "0", message = "Internal marks cannot be negative")
    @DecimalMax(value = "20", message = "Internal marks cannot exceed 20")
    private Double internalMarks;

    @Schema(
            description = "Updated practical examination marks",
            example = "17.0"
    )
    @DecimalMin(value = "0", message = "Practical marks cannot be negative")
    @DecimalMax(value = "20", message = "Practical marks cannot exceed 20")
    private Double practicalMarks;

    @Schema(
            description = "Updated final examination marks",
            example = "19.5"
    )
    @DecimalMin(value = "0", message = "Final marks cannot be negative")
    @DecimalMax(value = "20", message = "Final marks cannot exceed 20")
    private Double finalMarks;

    @Schema(
            description = "Remarks about the student's performance",
            example = "Improved performance after re-evaluation."
    )
    @Size(
            max = 500,
            message = "Remarks cannot exceed 500 characters"
    )
    private String remarks;
}