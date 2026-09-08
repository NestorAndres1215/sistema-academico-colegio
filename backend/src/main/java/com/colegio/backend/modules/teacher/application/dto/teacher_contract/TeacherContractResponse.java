package com.colegio.backend.modules.teacher.application.dto.teacher_contract;

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
