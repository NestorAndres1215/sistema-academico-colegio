package com.colegio.backend.modules.teacher.infrastructure.persistence.entity;

import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador de la observación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacher; // Profesor al que pertenece la observación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observer_id", nullable = false)
    private UserEntity observer; // Usuario que registra la observación

    @Column(nullable = false, length = 150)
    private String title; // Título de la observación

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // Descripción detallada de la observación

    @Column(nullable = false, name = "observation_date")
    private LocalDate observationDate; // Fecha en la que ocurrió la situación

    @Column(nullable = false)
    private LocalDateTime createdAt; // Fecha y hora de creación del registro

    private LocalDateTime updatedAt; // Fecha y hora de la última actualización
}