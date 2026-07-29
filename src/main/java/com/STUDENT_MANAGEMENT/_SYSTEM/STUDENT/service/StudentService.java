package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.service;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentProfileResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest request);
    StudentResponse updateStudent( Long studentId,StudentUpdateRequest request);
    void deleteStudent(Long studentId);
    StudentResponse getStudent(Long studentId);
    StudentProfileResponse getStudentByUserId(Long userId);
    List<StudentSummaryResponse> getAllStudents();
    Page<StudentSummaryResponse> getStudents(Pageable pageable);
    Page<StudentSummaryResponse> searchStudents(String keyword, Pageable pageable);
}
