package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary information about a student's marks")
public class MarksSummaryResponse {

    @Schema(
            description = "Unique marks record ID",
            example = "15"
    )
    private Long id;

    @Schema(
            description = "Student full name",
            example = "Ashmina Khatun"
    )
    private String studentName;

    @Schema(
            description = "Course name",
            example = "Java Programming"
    )
    private String courseName;

    @Schema(
            description = "Overall percentage obtained",
            example = "90.83"
    )
    private Double percentage;

    @Schema(
            description = "Grade awarded",
            example = "A+"
    )
    private Grade grade;

    @Schema(
            description = "Result status",
            example = "PASS"
    )
    private ResultStatus resultStatus;
}