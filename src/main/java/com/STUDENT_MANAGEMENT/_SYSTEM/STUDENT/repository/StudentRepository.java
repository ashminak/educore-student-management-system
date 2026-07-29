package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    /*
        ===========================
        BASIC QUERY
        ===========================
     */
    Optional<StudentEntity> findByRollNo(String rollNo);
    Optional<String> findByUser(User user);
    Optional<StudentEntity> findByUserId(Long userId);
    boolean existsByRollNo(String rollNo);
    boolean existsByUser(User user);

    /*
    =====================
     ACTIVE STUDENTS
    =====================
     */
    List<StudentEntity> findByActiveTrue();
    Page<StudentEntity> findByActiveTrue(Pageable pageable);
    /*
    =====================
    DEPARTMENT
    =====================
     */
    List<StudentEntity> findByDepartmentId(Long departmentId);
    Page<StudentEntity> findByDepartmentId(Long departmentId, Pageable pageable);
    /*
    ========================
    SEMESTER
    ========================
     */
    List<StudentEntity> findBySemester(Integer semester);
    /*
    ========================
    DEPARTMENT+SEMESTER
     */
    List<StudentEntity> findByDepartmentIdAndSemester(Long departmentId, Integer semester);
    /*
    ========================
    ROLL NUMBER
    ========================
     */
    List<StudentEntity> findByRollNoContainingIgnoreCase(String rollNo);
    /*
    ========================
    GUARDIAN NAME
    ========================
     */
    List<StudentEntity> findByGuardianNameContainingIgnoreCase(String guardianName);
    /*
    ========================
    PHONE
    ========================
     */
    List<StudentEntity> findByPhone(String phone);

    /*
    JPQL SEARCH
     */
    @Query("""
                SELECT s
                FROM StudentEntity s
                WHERE 
                LOWER(s.user.fullName)
                LIKE LOWER(CONCAT('%',:keyword,'%'))
                OR
                LOWER(s.rollNo)
                LIKE LOWER(CONCAT('%',:keyword,'%'))                                                                                                 
              """)
    Page<StudentEntity> searchStudent(@Param("keyword") String keyword, Pageable pageable);

    /*
    ==========================
    COUNT
    ==========================
     */
    Long countByDepartmentId(Long departmentId);
    Long countBySemester(Integer semester);
    Long countByActiveTrue();
}
