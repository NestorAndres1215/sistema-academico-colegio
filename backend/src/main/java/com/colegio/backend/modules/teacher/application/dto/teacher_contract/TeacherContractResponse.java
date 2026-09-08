package com.colegio.backend.modules.teacher.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherContractResponse (
        Long id,
        String code,
        String name,
        String lastName,
        String contractType,
        LocalDate startDate,
        LocalDate endDate,
        String position,
        Integer weeklyHours,
        BigDecimal salary,
        String status
){
}
