package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.dto.response.StudentCourseResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> ,
        JpaSpecificationExecutor<EnrollmentEntity> {

    /*
    BASIC
     */
    Optional<EnrollmentEntity> findByStudentAndCourseAndAcademicYear(StudentEntity student, CourseEntity course,String academicYear);
    boolean existsByStudentAndCourseAndAcademicYear(StudentEntity student, CourseEntity course, String academicYear);

    /*
    STUDENT
     */

    List<EnrollmentEntity> findByStudentId(Long studentId);
    Page<EnrollmentEntity> findByStudentId(Long studentId, Pageable pageable);

    /*
    COURSE
     */
    List<EnrollmentEntity> findByCourseId(Long courseId);
    Page<EnrollmentEntity> findByCourseId(Long courseId, Pageable pageable);
    /*
    STATUS
     */
    List<EnrollmentEntity> findByStatus(EnrollmentStatus status);
    /*
    ACADEMIC YEAR
     */
    List<EnrollmentEntity> findByAcademicYear(String academicYear);
    /*
    COURSE+STATUS
     */
    List<EnrollmentEntity> findByCourseIdAndStatus(Long courseId, EnrollmentStatus status);
    /*
    STUDENT+STATUS
     */
    List<EnrollmentEntity> findByStudentIdAndStatus(Long studentId, EnrollmentStatus status);
    @Query("""
            SELECT e 
            FROM EnrollmentEntity e
            WHERE
            LOWER(e.student.user.fullName)
            LIKE LOWER(CONCAT('%',:keyword,'%'))
            OR   
            LOWER(e.course.courseName)
            LIKE LOWER(CONCAT('%',:keyword,'%'))  
            OR
            LOWER(e.academicYear)
            LIKE LOWER(CONCAT('%',:keyword,'%'))             
                                                                                               
           """)
    Page<EnrollmentEntity> searchEnrollments(@Param("keyword") String keyword, Pageable pageable);
    /*
    COUNT
     */
    Long countByStatus(EnrollmentStatus status);
    Long countByStudentId(Long studentId);
    Long countByCourseId(Long courseId);

}
