package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching student marks")
public class MarksSearchRequest {

    @Schema(
            description = "Student ID",
            example = "1"
    )
    private Long studentId;

    @Schema(
            description = "Course ID",
            example = "10"
    )
    private Long courseId;

    @Schema(
            description = "Result status",
            example = "PASS",
            allowableValues = {
                    "PASS",
                    "FAIL",
                    "ABSENT",
                    "PENDING"
            }
    )
    private ResultStatus status;

    @Schema(
            description = "Student grade",
            example = "A+"
    )
    private Grade grade;
}