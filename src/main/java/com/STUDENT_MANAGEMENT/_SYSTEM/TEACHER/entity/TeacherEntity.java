package com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.enumm.Designation;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "teachers",
        indexes = {
                @Index(
                        name = "idx_employee_code",
                        columnList = "employee_code"
                ),
                @Index(
                        name = "idx_teacher_department",
                        columnList = "department_id"
                )
        }
)
public class TeacherEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;
    @Column(
            name = "employee_code",
            nullable = false,
            unique = true
    )
    private String employeeCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Designation designation;
    @Column(nullable = false)
    private String qualification;
    @Column(nullable = false)
    private Integer experienceYears;
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate joiningDate;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(nullable = false)
    private String phone;
    @Column( nullable = false,
    length = 1000)
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Builder.Default
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private DepartmentEntity department;

    @ManyToMany(
            mappedBy = "teachers"

    )
    @Builder.Default
    private Set<CourseEntity> course = new HashSet<>();

}
