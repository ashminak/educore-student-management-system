package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Information about a top-performing student displayed on the admin dashboard")
public class TopStudentResponse {

    @Schema(
            description = "Unique student ID",
            example = "101"
    )
    private Long studentId;

    @Schema(
            description = "Full name of the student",
            example = "Rahul Sharma"
    )
    private String studentName;

    @Schema(
            description = "Course in which the student is enrolled",
            example = "Bachelor of Computer Applications"
    )
    private String course;

    @Schema(
            description = "Department of the student",
            example = "Computer Science"
    )
    private String department;

    @Schema(
            description = "Overall percentage obtained by the student",
            example = "92.75"
    )
    private Double percentage;

    @Schema(
            description = "Final grade of the student",
            example = "A+"
    )
    private String grade;
}