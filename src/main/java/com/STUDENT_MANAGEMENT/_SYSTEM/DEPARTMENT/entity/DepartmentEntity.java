package com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "departments",
        indexes = {
                @Index(
                        name = "idx_department_code",
                        columnList = "department_code"
                ),
                @Index(
                        name = "idx_department_name",
                        columnList = "department_name"
                )
        }
)
public class DepartmentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            name = "department_code",
            unique = true,
            length = 100
    )
    private String departmentCode;
    @Column(
            nullable = false,
            name = "department_name",
            unique = true,
            length = 100
    )
    private String departmentName;
    @Column(length = 1000)
    private String description;
    @Column(length = 100)
    private String headOfDepartment;
    @Column(length = 200)
    private String officeLocation;
    @Column(length = 100)
    private String email;
    @Column(length = 15)
    private String phone;
    @Builder.Default
    private Boolean active =true;
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<StudentEntity> students = new ArrayList<>();
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<TeacherEntity> teachers = new ArrayList<>();
    @OneToMany(
            mappedBy = "department",
            fetch =FetchType.LAZY
    )
    @Builder.Default
    private List<CourseEntity> courses = new ArrayList<>();


}
