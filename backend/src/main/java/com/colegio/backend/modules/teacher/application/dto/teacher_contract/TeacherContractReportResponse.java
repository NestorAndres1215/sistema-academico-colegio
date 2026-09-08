package com.colegio.backend.modules.teacher.application.dto.teacher_contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TeacherContractReportResponse(

        Long id,

        String contractType,

        LocalDate startDate,

        LocalDate endDate,

        String position,

        Integer weeklyHours,

        BigDecimal salary,

        String status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Long teacherId,

        String teacherCode,

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

        String professionalLicenseNumber,

        String photo,

        String teacherStatus,

        LocalDateTime teacherCreatedAt,

        LocalDateTime teacherUpdatedAt

) {
}

