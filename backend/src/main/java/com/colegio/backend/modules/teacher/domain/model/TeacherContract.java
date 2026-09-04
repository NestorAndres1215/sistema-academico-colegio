package com.colegio.backend.modules.teacher.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TeacherContract {

    private Long id; // Identificador del contrato

    private Teacher teacher; // Profesor asociado

    private String contractType; // Tipo de contrato

    private LocalDate startDate; // Fecha de inicio

    private LocalDate endDate; // Fecha de finalización

    private String position; // Cargo

    private Integer weeklyHours; // Horas semanales

    private BigDecimal salary; // Remuneración / salario

    private String status; // Estado del contrato

    private LocalDateTime createdAt; // Fecha de creación

    private LocalDateTime updatedAt; // Fecha de actualización

}
