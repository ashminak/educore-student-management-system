package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.service;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository.DepartmentRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.request.TeacherUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.dto.response.TeacherSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.mapper.TeacherMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.repository.TeacherRepository;
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
public class TeacherServiceImpl  implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    @Transactional
    public TeacherResponse createTeacher(TeacherCreateRequest request) {
        if(teacherRepository.existsByEmployeeCode(request.getEmployeeCode())) {
            throw new RuntimeException("Roll Number already exists ");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(teacherRepository.existsByUser(user)) {
            throw new RuntimeException("Teacher Profile already exists");
        }
        TeacherEntity teacher = teacherMapper.toEntity(request);
        DepartmentEntity department =
                departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() ->
                                new RuntimeException("Department not found"));

        teacher.setDepartment(department);
        teacher.setUser(user);
        TeacherEntity saveTeacherEntity = teacherRepository.save(teacher);
        log.info("Teacher Created Successfully");
        return teacherMapper.toResponse(saveTeacherEntity);
    }
    @Override
    @Transactional
    public TeacherResponse updateTeacher(Long teacherId, TeacherUpdateRequest request) {

        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // First update normal fields
        teacherMapper.updateTeacher(request, teacher);

        // Then update department if provided
        if (request.getDepartmentId() != null) {
            DepartmentEntity department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            teacher.setDepartment(department);
        }

        TeacherEntity updatedTeacher = teacherRepository.save(teacher);

        log.info("Teacher Updated Successfully");

        return teacherMapper.toResponse(updatedTeacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long teacherId) {
        TeacherEntity teacher =teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setActive(false);
        teacherRepository.save(teacher);
        log.info("Teacher soft Deleted Successfully");

    }

    @Override
    public List<TeacherSummaryResponse> getAllTeachers() {
        return teacherMapper.toSummaryList(teacherRepository.findByActiveTrue());
    }

    @Override
    public Page<TeacherSummaryResponse> getAllTeachers(Pageable pageable) {
        return teacherRepository.findByActiveTrue(pageable)
                .map(teacherMapper::toSummary);
    }

    @Override
    public TeacherResponse getTeacher(Long teacherId) {
        TeacherEntity teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toResponse(teacher);
    }

    @Override
    public TeacherResponse getTeacherByUserId(Long userId) {
        TeacherEntity teacher = teacherRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        return teacherMapper.toResponse(teacher);
    }

    @Override
    public Page<TeacherSummaryResponse> searchTeachers(String keyword, Pageable pageable) {
       return teacherRepository.searchTeacher(keyword, pageable)
               .map(teacherMapper::toSummary);
    }
}
