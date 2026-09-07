package com.colegio.backend.modules.teacher.application.dto;

public record TeacherListResponse(

        Long id,
        String code,
        String email,
        String username,
        String name,
        String lastName,
        String dni,
        String birthDate,
        String phone,
        String specialty,
        String academicDegree,
        String professionalLicenseNumber,
        String university,
        Integer yearsOfExperience,
        String notes
) {
}
