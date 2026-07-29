package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.repository;

import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity ,Long> {
    /*
    BASIC
     */
    Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);
    Optional<DepartmentEntity> findByDepartmentName(String departmentName);
    boolean existsByDepartmentCode(String departmentCode);
    boolean existsByDepartmentName(String departmentName);

    /*
    ACTIVE DEPARTMENT
     */
    List<DepartmentEntity> findByActiveTrue();
    Page<DepartmentEntity> findByActiveTrue(Pageable pageable);
    /*
    SEARCH BY NAME
     */
    List<DepartmentEntity> findByDepartmentNameContainingIgnoreCase(String keyword);
    /*
    SEARCH BY CODE
     */
    List<DepartmentEntity> findByDepartmentCodeContainingIgnoreCase(String keyword);
    /*
    SEARCH
     */
    @Query("""
            SELECT d
            FROM DepartmentEntity d
            WHERE
            LOWER(d.departmentName)
            LIKE LOWER(CONCAT('%',:keyword,'%'))
            OR
            LOWER(d.departmentCode)
            LIKE LOWER(CONCAT('%',:keyword,'%'))                                                                             
           """)
    Page<DepartmentEntity> searchDepartment(@Param("keyword") String keyword, Pageable pageable);

        /*
        COUNT
         */
        Long countByActiveTrue();
}
