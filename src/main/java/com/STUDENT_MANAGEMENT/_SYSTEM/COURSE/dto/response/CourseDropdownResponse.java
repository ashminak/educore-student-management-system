package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Course information for dropdown selection")
public class CourseDropdownResponse {

    @Schema(
            description = "Unique course ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Course name",
            example = "Introduction to Java"
    )
    private String courseName;
}