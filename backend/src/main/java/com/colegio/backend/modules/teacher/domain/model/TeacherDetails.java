package com.colegio.backend.modules.teacher.domain.model;


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
public class TeacherDetails {

    private Long id; // Identificador del detalle

    private Teacher teacher; // Profesor asociado

    private String university; // Universidad

    private LocalDate graduationDate; // Fecha de titulación

    private Integer yearsOfExperience; // Años de experiencia

    private String curriculum; // Currículum vitae

    private String notes; // Observaciones

    private LocalDateTime createdAt; // Fecha de creación

    private LocalDateTime updatedAt; // Fecha de actualización

}
