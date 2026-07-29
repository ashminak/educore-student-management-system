package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.request.CourseUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseDropdownResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.dto.response.CourseSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.mapper.CourseMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.repository.CourseRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository.DepartmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseMapper courseMapper;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public CourseResponse createCourse(CourseCreateRequest request) {
        if(courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course code already exists");
        }
        DepartmentEntity department =departmentRepository.findById(
                request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Set<TeacherEntity> teachers = new HashSet<>(
                teacherRepository.findAllById(request.getTeacherIds())
        );
        CourseEntity course = courseMapper.toEntity(request);
        course.setDepartment(department);
        course.setTeachers(teachers);
        course.setStatus(CourseStatus.ACTIVE);
        CourseEntity savedCourse = courseRepository.save(course);
        log.info("Course created: {}", savedCourse.getId());
        return courseMapper.toResponse(savedCourse);
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Long courseId, CourseUpdateRequest request) {
       CourseEntity course = courseRepository.findById(courseId)
               .orElseThrow(() -> new RuntimeException("Course not found"));
       courseMapper.updateCourse(request,course);
       CourseEntity updatedCourse = courseRepository.save(course);
       log.info("Course updated: {}", updatedCourse.getId());
       return courseMapper.toResponse(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        CourseEntity course =  courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setActive(false);
        courseRepository.save(course);
        log.info("Course deleted: {}", course.getId());

    }

    @Override
    public List<CourseSummaryResponse> getAllCourses() {

        return courseMapper.toSummaryList(courseRepository.findByStatus(CourseStatus.ACTIVE));
    }

    @Override
    public Page<CourseSummaryResponse> getCourses(Pageable pageable) {

        return courseRepository.findByStatus(
                CourseStatus.ACTIVE,pageable)
                .map(courseMapper::toSummary);
    }

    @Override
    public CourseResponse getCourse(Long courseId) {
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toResponse(course);
    }

    @Override
    public Page<CourseSummaryResponse> searchCourse(String keyword, Pageable pageable) {

        return courseRepository.searchCourse(
                keyword,pageable)
                .map(courseMapper::toSummary);
    }

    @Override
    public List<CourseDropdownResponse> getDropdown() {

        return courseMapper.toDropDownList(
                courseRepository.findByStatus(CourseStatus.ACTIVE)
        );
    }
}
