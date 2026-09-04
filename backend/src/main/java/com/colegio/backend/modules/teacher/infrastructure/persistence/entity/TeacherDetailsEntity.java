package com.colegio.backend.modules.teacher.infrastructure.persistence.entity;

import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_details") // Detalle del profesor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador del detalle

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher; // Profesor asociado

    @Column(name = "university")
    private String university; // Universidad

    @Column(name = "graduation_date")
    private LocalDate graduationDate; // Fecha de titulación

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience; // Años de experiencia

    @Column(name = "curriculum")
    private String curriculum; // Currículum vitae

    @Column(name = "notes")
    private String notes; // Observaciones

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Fecha de creación

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Fecha de actualización
}