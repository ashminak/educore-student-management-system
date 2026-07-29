package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.enumm.Designation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherSearchRequest {
    @Schema(
            description = "Search keyword",
            example = "Ashmina"
    )
    private String keyword;
    @Schema(
            description = "Department name",
            example = "Computer Science"
    )
    private String department;
    @Schema(
            description = "Teacher designation",
            example = "ASSISTANT_PROFESSOR"
    )
    private Designation designation;
    @Schema(
            description = "Student active status",
            example = "true"
    )
    private Boolean active;

}
