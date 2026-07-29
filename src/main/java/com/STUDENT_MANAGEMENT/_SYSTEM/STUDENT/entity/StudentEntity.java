package com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.BloodGroup;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.enums.Gender;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(
        name = "students",
        indexes = {
                @Index(
                        name="idx_roll_number",
                        columnList = "roll_number"
                ),
                @Index(
                        name = "idx_department",
                        columnList = "department_id"
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity extends BaseEntity {
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
            name = "roll_number",
            nullable = false,
            unique = true,
            length = 20
    )
    private String rollNo;
    @Column(nullable = false)
    private Integer semester;
    @Column(nullable = false)
    private LocalDate admissionDate;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String phone;
    @Column(length = 500)
    private String address;
    @Column(length = 100)
    private String guardianName;
    @Column(length = 20)
    private String guardianPhone;
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;
    @Builder.Default
    private Boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private DepartmentEntity department;
    @ManyToMany(
            mappedBy = "students",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<CourseEntity> courses = new HashSet<>();

    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @Builder.Default
    private List<EnrollmentEntity> enrollments = new ArrayList<>();
}
