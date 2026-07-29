package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.service;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.repository.CourseRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.request.EnrollmentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.EnrollmentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.StudentCourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.mapper.EnrollmentMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.repository.EnrollmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    @Transactional
    public EnrollmentResponse createEnrollment(EnrollmentCreateRequest request) {
        StudentEntity student = studentRepository.findById(
                request.getStudentId())
                .orElseThrow(()->
                        new RuntimeException("Student not found"));
        CourseEntity course = courseRepository.findById(
                request.getCourseId())
                .orElseThrow(()->
                        new RuntimeException("Course not found"));
        if(enrollmentRepository.existsByStudentAndCourseAndAcademicYear(student,course,request.getAcademicYear())){
            throw new RuntimeException("Student already enrolled");
        }
        EnrollmentEntity enrollment = enrollmentMapper.toEntity(request);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollmentRepository.save(enrollment);
        log.info("Student {} enrolled into course {}",student.getId(),course.getId());
        return enrollmentMapper.toResponse(enrollment);

    }

    @Override
    @Transactional
    public EnrollmentResponse updateEnrollment(EnrollmentUpdateRequest request, Long enrollmentId) {
       EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
               .orElseThrow(()->
                       new RuntimeException("Enrollment not found"));
       enrollmentMapper.updateEntity(request,enrollment);
       EnrollmentEntity updatedEnrollment=enrollmentRepository.save(enrollment);
       return enrollmentMapper.toResponse(updatedEnrollment);
    }

    @Override
    @Transactional
    public void deleteEnrollment(Long enrollmentId) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(()->
                        new RuntimeException("Enrollment not found"));

        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollment.setActive(false);
        enrollmentRepository.save(enrollment);
        log.info("Enrollment {} has been deleted/dropped",enrollmentId);
    }

    @Override
    public EnrollmentResponse getEnrollment(Long enrollmentId) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(()->
                        new RuntimeException("Enrollment not found"));
        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    public List<EnrollmentSummaryResponse> getAllEnrollments() {
        return enrollmentMapper.toSummaryList(enrollmentRepository.findAll());
    }

    @Override
    public Page<EnrollmentSummaryResponse> getAllEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable).map(enrollmentMapper::toSummaryResponse);
    }

    @Override
    public Page<EnrollmentSummaryResponse> searchEnrollments(Pageable pageable, String keyword) {
        return enrollmentRepository.searchEnrollments(keyword,pageable).map(enrollmentMapper::toSummaryResponse);
    }

    @Override
    public List<StudentCourseResponse> toStudentCourseList(Long studentId) {
        return enrollmentMapper.toStudentCourseList(enrollmentRepository.findByStudentId(studentId));
    }
}
