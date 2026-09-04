package com.colegio.backend.modules.teacher.infrastructure.persistence.entity;

import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador del profesor

    @Column(unique = true, nullable = false)
    private String code; // Código del profesor

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user; // Usuario asociado

    @Column(name = "first_name")
    private String firstName; // Primer nombre

    @Column(name = "middle_name")
    private String middleName; // Segundo nombre

    @Column(name = "paternal_last_name")
    private String paternalLastName; // Apellido paterno

    @Column(name = "maternal_last_name")
    private String maternalLastName; // Apellido materno

    private String dni; // Documento Nacional de Identidad

    @Column(name = "birth_date")
    private LocalDate birthDate; // Fecha de nacimiento

    private String gender; // Sexo

    @Column(name = "marital_status")
    private String maritalStatus; // Estado civil

    private String phone; // Teléfono

    private String address; // Dirección

    private String specialty; // Especialidad

    @Column(name = "academic_degree")
    private String academicDegree; // Grado académico

    @Column(name = "professional_license_number")
    private String professionalLicenseNumber; // Número de colegiatura

    private String photo; // Fotografía

    private String status; // Estado del profesor

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Fecha de creación

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Fecha de actualización
}