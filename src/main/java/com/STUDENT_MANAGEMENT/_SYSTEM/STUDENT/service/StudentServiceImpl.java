package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.service;


import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository.DepartmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.request.StudentUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentProfileResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.dto.response.StudentSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.mapper.StudentMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.repository.StudentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private  final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public StudentResponse createStudent(StudentCreateRequest request) {
        if(studentRepository.existsByRollNo(request.getRollNo())) {
            throw new RuntimeException("Roll Number already exists");
        }
        User user = userRepository.findById(
                request.getUserId())
                .orElseThrow(()->
        new RuntimeException("User not found"));
        DepartmentEntity department = departmentRepository.findById(
                        request.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
        if(studentRepository.existsByUser(user)) {
            throw new RuntimeException("Student Profile already exists");
        }
        StudentEntity student = studentMapper.toEntity(request);
        student.setUser(user);
        student.setDepartment(department);
        StudentEntity savedStudent = studentRepository.save(student);
        log.info("Student created {}", savedStudent.getRollNo());
        return studentMapper.toResponse(savedStudent);

    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Long studentId, StudentUpdateRequest request) {
      StudentEntity student=  studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student not found"));
      studentMapper.updateStudent(request,student);
      StudentEntity updateStudent = studentRepository.save(student);
      log.info("Student updated {}", updateStudent.getRollNo());
      return studentMapper.toResponse(updateStudent);

    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student not found"));
        student.setActive(false);
        studentRepository.save(student);
        log.info("Student soft deleted {}", student.getRollNo());
    }

    @Override
    public StudentResponse getStudent(Long studentId) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student not found"));
        return studentMapper.toResponse(student);
    }

    @Override
    public StudentProfileResponse getStudentByUserId(Long userId) {
        StudentEntity student = studentRepository.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("Student not found"));
        return studentMapper.toProfile(student);
    }

    @Override
    public List<StudentSummaryResponse> getAllStudents() {
      return studentMapper.toSummaryList(
              studentRepository.findByActiveTrue()
      );
    }

    @Override
    public Page<StudentSummaryResponse> getStudents(Pageable pageable) {
       return studentRepository.findByActiveTrue(pageable)
               .map(studentMapper::toSummary);
    }

    @Override
    public Page<StudentSummaryResponse> searchStudents(String keyword, Pageable pageable) {
      return studentRepository.searchStudent(keyword, pageable)
              .map(studentMapper::toSummary);
    }
}
