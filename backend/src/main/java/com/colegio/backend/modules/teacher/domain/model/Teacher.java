package com.colegio.backend.modules.teacher.domain.model;

import com.colegio.backend.modules.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Teacher {

    private Long id; // Identificador del profesor

    private String code; // Código del profesor

    private User user; // Usuario asociado

    private String firstName; // Primer nombre

    private String middleName; // Segundo nombre

    private String paternalLastName; // Apellido paterno

    private String maternalLastName; // Apellido materno

    private String dni; // Documento Nacional de Identidad

    private LocalDate birthDate; // Fecha de nacimiento

    private String gender; // Sexo

    private String maritalStatus; // Estado civil

    private String phone; // Teléfono

    private String address; // Dirección

    private String specialty; // Especialidad

    private String academicDegree; // Grado académico

    private String professionalLicenseNumber; // Número de colegiatura

    private String photo; // Fotografía

    private String status; // Estado del profesor

    private LocalDateTime createdAt; // Fecha de creación

    private LocalDateTime updatedAt; // Fecha de actualización
}
