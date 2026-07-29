package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request object for creating a new course")
public class CourseCreateRequest {

    @Schema(
            description = "Unique course code",
            example = "CS101",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Course code is required")
    @Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
    private String courseCode;

    @Schema(
            description = "Course name",
            example = "Introduction to Java",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @Schema(
            description = "Course description",
            example = "This course covers Java fundamentals, OOP concepts, and collections."
    )
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Schema(
            description = "Number of credits",
            example = "4",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Credits are required")
    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 10, message = "Credits cannot exceed 10")
    private Integer credits;

    @Schema(
            description = "Semester in which the course is offered",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester cannot exceed 8")
    private Integer semester;

    @Schema(
            description = "Type of course",
            example = "CORE",
            allowableValues = {
                    "CORE",
                    "ELECTIVE",
                    "LAB"
            },
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Course type is required")
    private CourseType courseType;

    @Schema(
            description = "Department ID to which the course belongs",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @Schema(
            description = "List of teacher IDs assigned to the course",
            example = "[1, 2, 3]"
    )
    private Set<Long> teacherIds;
}