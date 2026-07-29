package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Detailed information about a course")
public class CourseResponse {

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
            description = "Course description",
            example = "This course covers Java fundamentals, OOP concepts, Collections Framework, and exception handling."
    )
    private String description;

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

    @Schema(
            description = "Type of the course",
            example = "CORE",
            allowableValues = {
                    "CORE",
                    "ELECTIVE",
                    "LAB"
            }
    )
    private CourseType courseType;

    @Schema(
            description = "Current course status",
            example = "ACTIVE",
            allowableValues = {
                    "ACTIVE",
                    "INACTIVE",
                    "COMPLETED"
            }
    )
    private CourseStatus courseStatus;

    @Schema(
            description = "Department ID offering this course",
            example = "1"
    )
    private Long departmentId;

    @Schema(
            description = "Department name",
            example = "Computer Science"
    )
    private String departmentName;

    @Schema(
            description = "Names of teachers assigned to this course",
            example = "[\"Rahul Sharma\", \"Priya Singh\"]"
    )
    private Set<String> teacherNames;
}