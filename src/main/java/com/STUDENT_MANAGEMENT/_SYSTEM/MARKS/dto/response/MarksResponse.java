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
@Schema(description = "Detailed information about a student's marks")
public class MarksResponse {

    @Schema(
            description = "Enrollment ID",
            example = "1"
    )
    private Long enrollmentId;

    @Schema(
            description = "Student ID",
            example = "101"
    )
    private Long studentId;

    @Schema(
            description = "Course ID",
            example = "10"
    )
    private Long courseId;

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
            description = "Internal assessment marks",
            example = "18.5"
    )
    private Double internalMarks;

    @Schema(
            description = "Practical examination marks",
            example = "17.0"
    )
    private Double practicalMarks;

    @Schema(
            description = "Final examination marks",
            example = "19.0"
    )
    private Double finalMarks;

    @Schema(
            description = "Total marks obtained",
            example = "54.5"
    )
    private Double totalMarks;

    @Schema(
            description = "Overall percentage",
            example = "90.83"
    )
    private Double percentage;

    @Schema(
            description = "Result status",
            example = "PASS"
    )
    private ResultStatus resultStatus;

    @Schema(
            description = "Grade awarded",
            example = "A+"
    )
    private Grade grade;

    @Schema(
            description = "Additional remarks",
            example = "Excellent performance."
    )
    private String remarks;
}