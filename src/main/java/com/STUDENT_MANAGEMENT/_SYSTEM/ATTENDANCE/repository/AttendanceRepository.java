package com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.entity.AttendanceEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.enums.AttendanceStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Long>,
        JpaSpecificationExecutor<AttendanceEntity> {
        /*
        BASIC
         */
    Optional<AttendanceEntity>  findByEnrollmentAndAttendanceDate(EnrollmentEntity enrollmentEntity, LocalDate attendanceDate);
    boolean existsByEnrollmentAndAttendanceDate(EnrollmentEntity enrollmentEntity, LocalDate attendanceDate);
    /*
    Enrollment
     */
    List<AttendanceEntity> findByEnrollmentId(Long enrollmentId);
    Page<AttendanceEntity> findByEnrollmentId(Long enrollmentId, Pageable pageable);
    /*
    ATTENDANCE DATE
     */
    List<AttendanceEntity> findByAttendanceDate(LocalDate attendanceDate);
    List<AttendanceEntity> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);
    /*
    STATUS
     */
    List<AttendanceEntity> findByStatus(AttendanceStatus status);
    /*
    STUDENT
     */
    List<AttendanceEntity> findByEnrollmentStudentId(Long studentId);
    /*
    COURSE
     */
    List<AttendanceEntity> findByEnrollmentCourseId(Long courseId);
    /*
    QUERY
     */
    @Query("""
            SELECT  a
            FROM AttendanceEntity a
            WHERE
            LOWER(a.enrollment.student.user.fullName)
            LIKE LOWER(CONCAT('%',:keyword,'%'))
            OR
            LOWER(a.enrollment.course.courseName)
            LIKE LOWER(CONCAT('%',:keyword,'%'))                                                            
            """)
    Page<AttendanceEntity> searchAttendance(@Param("keyword") String keyword,Pageable pageable);
    /*
    COUNT
     */
    Long countByEnrollmentStudentId(Long studentId);
    Long countByEnrollmentCourseId(Long courseId);
    Long countByEnrollmentId(Long enrollmentId);
    Long countByEnrollmentIdAndStatus(Long enrollmentId, AttendanceStatus status);

    @Query(value = """
SELECT AVG(percentage)
FROM (
    SELECT
        (SUM(CASE WHEN status = 'PRESENT' THEN 1 ELSE 0 END) * 100.0) / COUNT(*) AS percentage
    FROM attendance
    WHERE active = true
    GROUP BY enrollment_id
) t
""", nativeQuery = true)
    Double findAverageAttendance();
}
