package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponse {
    private Long id;
    private Long enrollmentId;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private LocalDate attendanceDate;
    private AttendanceStatus status;
    private String remarks;
}
