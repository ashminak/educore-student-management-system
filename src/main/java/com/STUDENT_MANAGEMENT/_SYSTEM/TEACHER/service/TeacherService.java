package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.service;

import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    TeacherResponse createTeacher(TeacherCreateRequest request);

    TeacherResponse updateTeacher(Long teacherId,TeacherUpdateRequest request);

    void deleteTeacher(Long teacherId);
    List<TeacherSummaryResponse> getAllTeachers();
    Page<TeacherSummaryResponse> getAllTeachers(Pageable pageable);
    TeacherResponse getTeacher(Long teacherId);
    TeacherResponse getTeacherByUserId(Long userId);
    Page<TeacherSummaryResponse> searchTeachers(
            String keyword,
            Pageable pageable);
}
