package com.colegio.backend.modules.teacher.application.dto.teacher_observation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateTeacherObservationRequest(

        @NotBlank(message = "El profesor es obligatorio")
        String teacher,

        @NotBlank(message = "El usuario es obligatorio")
        String usuario,

        @NotBlank(message = "El título es obligatorio")
        String title,

        @NotBlank(message = "La descripción es obligatoria")
        String description,

        @NotNull(message = "La fecha de observación es obligatoria")
        LocalDate observationDate

) {
}