package com.colegio.backend.infrastructure.persistence.entity;

import com.colegio.backend.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_observation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherObservationEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdministratorEntity admin;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
