package com.STUDENT_MANAGEMENT._SYSTEM.PDF.service;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;

public interface PdfService {
    byte[] generateStudentReport(Long studentId);
    byte[] generateTeacherReport(Long teacherId);
    byte[] generateAttendanceReport(Long attendanceId);
    byte[] generateMarksReport(Long marksId);
}
