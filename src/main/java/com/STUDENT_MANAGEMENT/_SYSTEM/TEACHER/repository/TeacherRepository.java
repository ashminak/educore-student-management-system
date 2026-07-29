package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.enumm.Designation;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {
    /*
    BASIC
     */
    Optional<TeacherEntity> findByEmployeeCode(String employeeCode);
    Optional<TeacherEntity> findByUser(User user);
    Optional<TeacherEntity> findByUserId(Long userId);
    boolean existsByEmployeeCode(String employeeCode);
    boolean existsByUser(User user);

    /*
    ACTIVE TEACHER
     */
    List<TeacherEntity> findByActiveTrue();
    Page<TeacherEntity> findByActiveTrue(Pageable pageable);

    /*
    DEPARTMENT
     */
    List<TeacherEntity> findByDepartmentId(Long departmentId);
    Page<TeacherEntity> findByDepartmentId(Long departmentId, Pageable pageable);
    /*
    DESIGNATION
     */
    List<TeacherEntity> findByDesignation(Designation designation);
    /*
    EXPERIENCE
     */
    List<TeacherEntity> findByExperienceYearsGreaterThanEqual(Integer years);
    /*
    PHONE
     */
    Optional<TeacherEntity> findByPhone(String phone);
    /*
    QUALIFICATION
     */
    List<TeacherEntity> findByQualificationContainingIgnoreCase(String qualification);

    /*
    SEARCH
     */
    @Query("""
               SELECT t
               FROM TeacherEntity t
               WHERE 
                    LOWER(t.user.fullName)
                    LIKE LOWER(CONCAT('%',:keyword,'%'))
               OR
                    LOWER(t.employeeCode)
                    LIKE LOWER(CONCAT('%',:keyword,'%'))
               OR
                    LOWER(t.department.departmentName)
                    LIKE LOWER(CONCAT('%',:keyword,'%'))                                                                                                   
                                            
            """)
    Page<TeacherEntity> searchTeacher(
            @Param("keyword")
            String keyword,
            Pageable pageable);
    /*
    COUNT
     */
    Long countByDepartmentId(Long departmentId);
    Long countByDesignation(Designation designation);
    Long countByActiveTrue();


}
