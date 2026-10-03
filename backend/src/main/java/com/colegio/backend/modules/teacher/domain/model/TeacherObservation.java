package com.colegio.backend.modules.teacher.domain.model;

import com.colegio.backend.modules.user.domain.model.User;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TeacherObservation {

    private Long id; // Identificador de la observación

    private Teacher teacher; // Profesor al que pertenece la observación

    private User observer; // Usuario que registra la observación

    private String title; // Título de la observación

    private String description; // Descripción detallada de la observación

    private LocalDate observationDate; // Fecha en la que ocurrió la situación

    private LocalDateTime createdAt; // Fecha y hora de creación del registro

    private LocalDateTime updatedAt; // Fecha y hora de la última actualización

}