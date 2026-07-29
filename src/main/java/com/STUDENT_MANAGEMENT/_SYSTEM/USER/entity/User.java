package com.STUDENT_MANAGEMENT._SYSTEM.USER.entity;

import com.STUDENT_MANAGEMENT._SYSTEM.NOTIFICATION.entity.NotificationEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.STUDENT.entity.StudentEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.TEACHER.entity.TeacherEntity;
import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
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
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_user_username",
                        columnNames = "username"
                )
        },
        indexes = {
                @Index(
                        name = "idx_user_email",
                        columnList = "email"
                ),
                @Index(
                        name = "idx_user_username",
                        columnList = "username"
                ),
                @Index(
                        name = "idx_user_role",
                        columnList = "role"
                )
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "full_name",
            nullable = false,
            length = 100
    )
    private String fullName;

    @Column(
            nullable = false,
            length = 100
    )
    private String email;

    @Column(
            nullable = false,
            length = 50
    )
    private String username;

    @Column(
            nullable = false
    )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private Role role;

    @Builder.Default
    @Column(nullable = false)
    private Boolean enabled = true;

    @Builder.Default
    @Column(
            name = "account_non_locked",
            nullable = false
    )
    private Boolean accountNumLocked = true;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private StudentEntity student;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private TeacherEntity teacher;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<NotificationEntity> notifications = new ArrayList<>();
}