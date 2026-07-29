package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Course information for a student's enrolled course")
public class StudentCourseResponse {

    @Schema(
            description = "Unique course ID",
            example = "10"
    )
    private Long courseId;

    @Schema(
            description = "Unique course code",
            example = "CS101"
    )
    private String courseCode;

    @Schema(
            description = "Course name",
            example = "Introduction to Java"
    )
    private String courseName;

    @Schema(
            description = "Number of course credits",
            example = "4"
    )
    private Integer credits;

    @Schema(
            description = "Semester in which the course is offered",
            example = "5"
    )
    private Integer semester;
}