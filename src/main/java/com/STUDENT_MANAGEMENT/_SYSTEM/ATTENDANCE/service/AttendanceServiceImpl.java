package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.service;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceCreateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.request.AttendanceUpdateRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendancePercentageResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.dto.response.AttendanceSummaryResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.entity.AttendanceEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.mapper.AttendanceMapper;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.repository.AttendanceRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceMapper attendanceMapper;
    @Override
    @Transactional
    public AttendanceResponse createAttendance(AttendanceCreateRequest request) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(
                request.getEnrollmentId())
                .orElseThrow(()->
                        new RuntimeException("Enrollment not found"));
        if(attendanceRepository.existsByEnrollmentAndAttendanceDate(enrollment,request.getAttendanceDate())){
            throw new RuntimeException("Attendance already exists");
        }
        AttendanceEntity attendanceEntity = attendanceMapper.toEntity(request);
        attendanceEntity.setEnrollment(enrollment);
        attendanceRepository.save(attendanceEntity);
        log.info("Attendance successfully created");
        return attendanceMapper.toResponse(attendanceEntity);
    }

    @Override
    @Transactional
    public AttendanceResponse updateAttendance(AttendanceUpdateRequest request, Long attendanceId) {
        AttendanceEntity attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(()->
                        new RuntimeException("Attendance not found"));
        attendanceMapper.toUpdate(request,attendance);
        AttendanceEntity updated = attendanceRepository.save(attendance);
        log.info("Attendance successfully updated");
        return attendanceMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteAttendance(Long attendanceId) {
        AttendanceEntity attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(()->
                        new RuntimeException("Attendance not found"));
        attendance.setActive(false);
        attendanceRepository.save(attendance);
        log.info("Attendance successfully deleted");
    }

    @Override
    public AttendanceResponse getAttendance(Long attendanceId) {
        AttendanceEntity attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(()->
                        new RuntimeException("Attendance not found"));
        return attendanceMapper.toResponse(attendance);
    }

    @Override
    public List<AttendanceSummaryResponse> getAllAttendance() {
        return attendanceMapper.toSummaryList(attendanceRepository.findAll());
    }

    @Override
    public Page<AttendanceSummaryResponse> getAllAttendance(Pageable pageable) {
        return attendanceRepository.findAll(pageable).map(attendanceMapper::toSummary);
    }

    @Override
    public Page<AttendanceSummaryResponse> searchAttendance(Pageable pageable, String keyword) {
        return attendanceRepository.searchAttendance( keyword,pageable).map(attendanceMapper::toSummary);
    }

    @Override
    public List<AttendanceSummaryResponse> getStudentAttendance(Long studentId) {
        return attendanceMapper.toSummaryList(attendanceRepository.findByEnrollmentStudentId(studentId));
    }

    @Override
    public List<AttendanceSummaryResponse> getCourseAttendance(Long courseId) {
        return attendanceMapper.toSummaryList(attendanceRepository.findByEnrollmentCourseId(courseId));
    }

    @Override
    public AttendancePercentageResponse getAttendancePercentage(Long enrollmentId) {
        long total = attendanceRepository.countByEnrollmentId(enrollmentId);
        long present = attendanceRepository.countByEnrollmentIdAndStatus(enrollmentId, AttendanceStatus.PRESENT);
        long absent = attendanceRepository.countByEnrollmentIdAndStatus(enrollmentId, AttendanceStatus.ABSENT);
        long late = attendanceRepository.countByEnrollmentIdAndStatus(enrollmentId, AttendanceStatus.LATE);
        long leave = attendanceRepository.countByEnrollmentIdAndStatus(enrollmentId, AttendanceStatus.LEAVE);

        double percentage =
                total == 0
                        ? 0
                        : (present * 100.0) / total;

        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(()->
                        new RuntimeException("Enrollment not found"));
        return  AttendancePercentageResponse.builder()
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getUser().getFullName())
                .courseId(enrollment.getCourse().getId())
                .courseName(enrollment.getCourse().getCourseName())
                .totalClasses((int)total)
                .presentClasses((int)present)
                .absentClasses((int)absent)
                .lateClasses((int)late)
                .leaveClasses((int)leave)
                .attendancePercentage(percentage)
                .build();
    }
}
