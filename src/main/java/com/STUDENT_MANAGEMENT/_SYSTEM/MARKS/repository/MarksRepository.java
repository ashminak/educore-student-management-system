package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface MarksRepository extends JpaRepository<MarksEntity, Long> ,
        JpaSpecificationExecutor<MarksEntity> {
    /*
    ENROLLMENT
     */
    Optional<MarksEntity> findByEnrollmentId(Long enrollmentId);
    boolean existsByEnrollmentId(Long enrollmentId);

    /*
    STUDENT
     */
    List<MarksEntity> findByEnrollmentStudentId(Long studentId);
    Page<MarksEntity> findByEnrollmentStudentId(Long studentId, Pageable pageable);
    /*
    COURSE
     */
    List<MarksEntity> findByEnrollmentCourseId(Long courseId);
    Page<MarksEntity> findByEnrollmentCourseId(Long courseId, Pageable pageable);
    /*
    GRADE
     */
    List<MarksEntity> findByGrade(Grade grade);
    /*
    RESULT-STATUS
     */
    List<MarksEntity> findByResultStatus(ResultStatus status);

    /*
    SEARCH
     */
    @Query("""
            SELECT m
            FROM MarksEntity m
            WHERE 
            LOWER(m.enrollment.student.user.fullName)
            LIKE LOWER (CONCAT('%',:keyword,'%'))
            OR
            LOWER(m.enrollment.course.courseName)
            LIKE LOWER (CONCAT('%',:keyword,'%'))
            """)
    Page<MarksEntity> searchMarks(@Param("keyword") String keyword, Pageable pageable);
    /*
    COUNT
     */
    Long countByResultStatus(ResultStatus status);
    Long countByGrade(Grade grade);
    /*
    TOP SCORE
     */
    List<MarksEntity> findTop10ByOrderByPercentageDesc();

    @Query("""
            SELECT AVG(m.percentage)
            FROM MarksEntity m
            WHERE m.active=true                        
            """)
    Double findAveragePercentage();
}
