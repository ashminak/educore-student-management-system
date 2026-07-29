package com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.service;

import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.admin.AdminDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.student.StudentDashboardResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DASHBOARD.dto.teacher.TeacherDashboardResponse;

public interface DashboardService {
    AdminDashboardResponse getAdminDashboard();
    TeacherDashboardResponse getTeacherDashboard(Long teacherId);
    StudentDashboardResponse getStudentDashboard(Long studentId);

}
