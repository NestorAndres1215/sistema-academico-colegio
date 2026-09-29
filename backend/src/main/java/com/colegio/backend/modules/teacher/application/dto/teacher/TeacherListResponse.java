package com.colegio.backend.modules.teacher.application.dto.teacher;

public record TeacherListResponse(

        Long id,
        String code,
        String email,
        String username,

        String name,
        String lastName,

        String firstName,
        String middleName,
        String paternalLastName,
        String maternalLastName,

        String dni,
        String birthDate,
        String gender,
        String maritalStatus,

        String phone,
        String address,

        String specialty,
        String academicDegree,
        String professionalLicenseNumber,

        String university,
        String graduationDate,
        Integer yearsOfExperience,
        String curriculum,
        String notes,

        String photo,
        String status
) {
}