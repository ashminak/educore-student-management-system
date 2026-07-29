package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for updating an existing course")
public class CourseUpdateRequest {

    @Schema(
            description = "Updated course name",
            example = "Advanced Java Programming"
    )
    @Size(
            min = 3,
            max = 100,
            message = "Course name must be between 3 and 100 characters"
    )
    private String courseName;

    @Schema(
            description = "Updated course description",
            example = "Advanced Java topics including Streams, Multithreading, Spring Boot, and Microservices."
    )
    @Size(
            max = 1000,
            message = "Description cannot exceed 1000 characters"
    )
    private String description;

    @Schema(
            description = "Updated course credits",
            example = "5"
    )
    @Min(
            value = 1,
            message = "Credits must be at least 1"
    )
    @Max(
            value = 10,
            message = "Credits cannot exceed 10"
    )
    private Integer credits;

    @Schema(
            description = "Updated semester",
            example = "6"
    )
    @Min(
            value = 1,
            message = "Semester must be at least 1"
    )
    @Max(
            value = 8,
            message = "Semester cannot exceed 8"
    )
    private Integer semester;

    @Schema(
            description = "Updated course type",
            example = "ELECTIVE",
            allowableValues = {
                    "CORE",
                    "ELECTIVE",
                    "LAB"
            }
    )
    private CourseType courseType;
}