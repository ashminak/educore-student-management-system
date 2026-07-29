package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for searching courses")
public class CourseSearchRequest {

    @Schema(
            description = "Search keyword for course name or course code",
            example = "Java"
    )
    private String keyword;

    @Schema(
            description = "Department ID",
            example = "1"
    )
    private Long departmentId;

    @Schema(
            description = "Semester number",
            example = "5"
    )
    private Integer semester;

    @Schema(
            description = "Course type",
            example = "CORE",
            allowableValues = {
                    "CORE",
                    "ELECTIVE",
                    "LAB"
            }
    )
    private CourseType courseType;
}