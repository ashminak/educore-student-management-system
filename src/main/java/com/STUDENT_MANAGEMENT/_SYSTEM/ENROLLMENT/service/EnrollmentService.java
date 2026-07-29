package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentSearchRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.StudentCourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(EnrollmentCreateRequest request);
    EnrollmentResponse updateEnrollment(EnrollmentUpdateRequest request, Long enrollmentId);
    void deleteEnrollment(Long enrollmentId);
    EnrollmentResponse getEnrollment(Long enrollmentId);
    List<EnrollmentSummaryResponse> getAllEnrollments();
    Page<EnrollmentSummaryResponse> getAllEnrollments(Pageable pageable);
    Page<EnrollmentSummaryResponse> searchEnrollments(Pageable pageable,String keyword);
    List<StudentCourseResponse> toStudentCourseList(Long studentId);

}
