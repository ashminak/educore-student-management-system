package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.enumm.Designation;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherProfileResponse {
    private String fullName;

    private String email;

    private String employeeCode;

    private String department;

    private Designation designation;

    private String qualification;

    private Integer experienceYears;

    private String phone;

    private String address;
}
