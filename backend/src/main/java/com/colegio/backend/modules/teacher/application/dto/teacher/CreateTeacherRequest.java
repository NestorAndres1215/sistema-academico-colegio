package com.colegio.backend.modules.teacher.application.dto;

import java.time.LocalDate;

public record CreateTeacherRequest(

        String firstName,
        String middleName,
        String paternalLastName,
        String maternalLastName,
        String dni,
        LocalDate birthDate,
        String gender,
        String maritalStatus,
        String phone,
        String address,
        String specialty,
        String academicDegree,
        String professionalLicenseNumber

) {
}
