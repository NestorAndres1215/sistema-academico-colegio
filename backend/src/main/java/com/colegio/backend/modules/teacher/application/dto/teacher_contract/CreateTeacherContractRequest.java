package com.colegio.backend.modules.teacher.application.dto.teacher_contract;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTeacherContractRequest(

        @NotBlank(message = "El tipo de contrato es obligatorio")
        @Size(max = 50, message = "El tipo de contrato no puede superar los 50 caracteres")
        String contractType,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate startDate,

        @NotNull(message = "La fecha de fin es obligatoria")
        LocalDate endDate,

        @NotBlank(message = "El cargo es obligatorio")
        @Size(max = 100, message = "El cargo no puede superar los 100 caracteres")
        String position,

        @NotNull(message = "Las horas semanales son obligatorias")
        @Min(value = 1, message = "Las horas semanales deben ser mayores a 0")
        @Max(value = 60, message = "Las horas semanales no pueden superar 60")
        Integer weeklyHours,

        @NotNull(message = "El salario es obligatorio")
        @DecimalMin(value = "0.01", message = "El salario debe ser mayor a 0")
        @Digits(integer = 10, fraction = 2, message = "El salario debe tener máximo 10 enteros y 2 decimales")
        BigDecimal salary
) {
}