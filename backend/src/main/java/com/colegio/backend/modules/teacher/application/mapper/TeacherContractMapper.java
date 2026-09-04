package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.shared.constant.StatusConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherContractMapper {

    public TeacherContract toDomain(TeacherRequest request, Teacher teacher) {

        return TeacherContract.builder()
                .teacher(teacher)
                .contractType(request.contractType())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .position(request.position())
                .weeklyHours(request.weeklyHours())
                .salary(request.salary())
                .status(StatusConstants.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
    }
}