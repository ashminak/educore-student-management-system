package com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.enums.CourseType;
import com.STUDENT_MANAGEMENT._SYSTEM.DEPARTMENT.entity.DepartmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "courses",
        indexes = {
                @Index(
                        name = "idx_course_code",
                        columnList = "course_code"
                ),
                @Index(
                        name = "idx_course_name",
                        columnList = "course_name"
                )
        }
)
@Builder
public class CourseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name="course_code",
            nullable = false,
            length = 20,
            unique = true
    )
    private String courseCode;
    @Column(
            name = "course_name",
            nullable = false,
            length = 100
    )
    private String courseName;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer credits;
    @Column(nullable = false)
    private Integer semester;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseType courseType;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CourseStatus status=CourseStatus.ACTIVE;
    @Builder.Default
    private Boolean active =true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private DepartmentEntity department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_courses",
            joinColumns =
            @JoinColumn(name = "course_id"),
            inverseJoinColumns =
            @JoinColumn(name = "teacher_id")
    )
    @Builder.Default
    private Set<TeacherEntity> teachers =new HashSet<>();

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<EnrollmentEntity>  enrollments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @Builder.Default
    private Set<StudentEntity> students = new HashSet<>();


}
