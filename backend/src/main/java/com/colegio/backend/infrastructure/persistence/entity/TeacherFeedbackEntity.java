package com.colegio.backend.infrastructure.persistence.entity;


import com.colegio.backend.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "teacher_feedback",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"teacher_id", "student_id", "period"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherFeedbackEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(nullable = false, length = 20)
    private String period;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}