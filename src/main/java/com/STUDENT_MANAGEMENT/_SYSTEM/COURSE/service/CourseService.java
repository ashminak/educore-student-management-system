package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
     CourseResponse createCourse(CourseCreateRequest request);
     CourseResponse updateCourse(Long courseId, CourseUpdateRequest request);
     void deleteCourse(Long courseId);
    List<CourseSummaryResponse> getAllCourses();
    Page<CourseSummaryResponse> getCourses(Pageable pageable);
    CourseResponse getCourse(Long courseId);

    Page<CourseSummaryResponse> searchCourse(
            String keyword,
            Pageable pageable);
    List<CourseDropdownResponse> getDropdown();
}
