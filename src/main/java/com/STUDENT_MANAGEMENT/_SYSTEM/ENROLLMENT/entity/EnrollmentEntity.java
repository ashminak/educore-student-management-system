package com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.ATTENDANCE.entity.AttendanceEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.COURSE.entity.CourseEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.EnrollmentStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity.MarksEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_student_course_year",
                        columnNames = {
                        "student_id",
                        "course_id",
                        "academic_year"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_student",
                        columnList = "student_id"
                ),
                @Index(
                        name = "idx_course",
                        columnList = "course_id"
                ),
                @Index(
                        name = "idx_year",
                        columnList = "academic_year"
                )
        }
)
@Builder
public class EnrollmentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false
    )
    private StudentEntity student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    private CourseEntity course;
    @Column(nullable = false)
    private Integer semester;

    @Column(
            nullable = false,
            name = "academic_year",
            length = 9
    )
    private String academicYear;
    @Column(
            nullable = false,
            name = "enrollment_date"
    )
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EnrollmentStatus status= EnrollmentStatus.ENROLLED;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Grade grade =Grade.NOT_ASSIGNED;

    @Builder.Default
    private Boolean active = true;
    @Builder.Default
    @OneToMany(
            mappedBy = "enrollment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    private List<AttendanceEntity> attendances = new ArrayList<>();

    @OneToOne(
            mappedBy = "enrollment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private MarksEntity marks;


}
