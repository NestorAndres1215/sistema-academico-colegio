package com.colegio.backend.modules.teacher.application.dto;

public record TeacherResponse(
        Long id,
        String code,
        String name,
        String lastName,
        String dni,
        String photo
) {
}
