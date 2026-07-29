package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePercentageResponse {
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private Integer totalClasses;
    private Integer presentClasses;
    private Integer absentClasses;
    private Integer lateClasses;
    private Integer leaveClasses;
    private Double attendancePercentage;
}
