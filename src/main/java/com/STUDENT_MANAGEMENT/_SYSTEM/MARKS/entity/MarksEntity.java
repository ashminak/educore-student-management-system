package com.STUDENT_MANAGEMENT._SYSTEM.MARKS.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.entity.EnrollmentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.ENROLLMENT.enums.Grade;
import com.STUDENT_MANAGEMENT._SYSTEM.MARKS.enums.ResultStatus;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "marks",
        indexes = {
                @Index(
                        name = "idx_marks_enrollment",
                        columnList = "enrollment_id"
                ),
                @Index(
                        name = "idx_marks_grade",
                        columnList = "grade"
                ),
                @Index(
                        name = "idx_marks_result_status",
                        columnList = "result_status"
                )
        }
)
public class MarksEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "enrollment_id",
            nullable = false,
            unique = true
    )
    private EnrollmentEntity enrollment;

    @Column(nullable = false)
    private Double internalMarks;

    @Column(nullable = false)
    private Double practicalMarks;

    @Column(nullable = false)
    private Double finalMarks;

    @Column(nullable = false)
    private Double totalMarks;
    @Column
    private Double percentage;

    @Enumerated(EnumType.STRING)
    @Column
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @Column
    private ResultStatus resultStatus;

    @Column(length = 500)
    private String remarks;

    @Builder.Default
    private Boolean active = true;
}