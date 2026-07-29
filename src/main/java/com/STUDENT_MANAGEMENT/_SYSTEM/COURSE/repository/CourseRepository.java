package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    /*
    BASIC
     */
    Optional<CourseEntity> findByCourseCode(String courseCode);
    Optional<CourseEntity> findByCourseName(String courseName);
    boolean existsByCourseCode(String courseCode);
    boolean existsByCourseName(String courseName);
    /*
    STATUS
     */
    List<CourseEntity> findByStatus(CourseStatus status);
    Page<CourseEntity> findByStatus(CourseStatus status, Pageable pageable);
    /*
    DEPARTMENT
     */
    List<CourseEntity> findByDepartmentId(Long departmentId);
    Page<CourseEntity> findByDepartmentId(Long departmentId, Pageable pageable);
    /*
    SEMESTER
     */
    List<CourseEntity> findBySemester(Integer semester);
    /*
    COURSE TYPE
     */
    List<CourseEntity> findByCourseType(CourseType courseType);
    /*
    DEPARTMENT+SEMESTER
     */
    List<CourseEntity> findByDepartmentIdAndSemester(Long departmentId, Integer semester);
    /*
    SEARCH
     */
    @Query("""
            SELECT c
            FROM CourseEntity c
            WHERE
                  LOWER(c.courseName)
                  LIKE LOWER(CONCAT('%',:keyword,'%'))
            OR
                  LOWER(c.courseCode)
                  LIKE LOWER(CONCAT('%',:keyword,'%'))                                                                             
           """)
    Page<CourseEntity> searchCourse(@Param("keyword") String keyword, Pageable pageable);
    /*
    COUNT
     */
    Long countByDepartmentId(Long departmentId);
    Long countBySemester(Integer semester);
    Long countByStatus(CourseStatus status);
}
