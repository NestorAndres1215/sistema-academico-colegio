package com.colegio.backend.modules.teacher.application.dto.teacher_observation;

import java.time.LocalDate;

public record TeacherObservationResponse(
        Long id,
        String teacher,
        String title,
        String description,
        LocalDate observationDate
) {
}
