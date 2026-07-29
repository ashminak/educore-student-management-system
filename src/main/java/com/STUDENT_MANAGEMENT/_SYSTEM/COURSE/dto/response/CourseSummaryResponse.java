package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Summary information about a course")
public class CourseSummaryResponse {

    @Schema(
            description = "Unique course ID",
            example = "1"
    )
    private Long id;

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
            description = "Department offering the course",
            example = "Computer Science"
    )
    private String departmentName;

    @Schema(
            description = "Number of credits",
            example = "4"
    )
    private Integer credits;

    @Schema(
            description = "Semester in which the course is offered",
            example = "5"
    )
    private Integer semester;
}