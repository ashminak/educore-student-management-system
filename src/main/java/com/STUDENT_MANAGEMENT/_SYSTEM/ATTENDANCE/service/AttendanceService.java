package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendancePercentageResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceService {
    /*
    CREATE
     */
    AttendanceResponse createAttendance(AttendanceCreateRequest request);
    AttendanceResponse updateAttendance(AttendanceUpdateRequest request,Long attendanceId);
    void deleteAttendance(Long attendanceId);
    AttendanceResponse getAttendance(Long attendanceId);
    List<AttendanceSummaryResponse> getAllAttendance();
    Page<AttendanceSummaryResponse> getAllAttendance(Pageable pageable);
    Page<AttendanceSummaryResponse> searchAttendance(Pageable pageable, String keyword);
    List<AttendanceSummaryResponse> getStudentAttendance(Long studentId);
    List<AttendanceSummaryResponse> getCourseAttendance(Long courseId);
    AttendancePercentageResponse getAttendancePercentage(Long enrollmentId);


}
