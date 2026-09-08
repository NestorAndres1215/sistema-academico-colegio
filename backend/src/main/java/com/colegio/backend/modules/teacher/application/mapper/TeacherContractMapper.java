package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.teacher_contract.CreateTeacherContractRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_contract.TeacherContractResponse;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.shared.constant.StatusConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.colegio.backend.shared.utils.StringUtils.joinNames;

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

    public TeacherContractResponse toResponse(TeacherContract domain) {

        if (domain == null) {
            return null;
        }

        Teacher teacher = domain.getTeacher();

        return new TeacherContractResponse(
                domain.getId(),
                teacher.getCode(),
                joinNames(teacher.getFirstName(), teacher.getMiddleName()),
                joinNames(teacher.getPaternalLastName(), teacher.getMaternalLastName()),
                domain.getContractType(),
                domain.getStartDate(),
                domain.getEndDate(),
                domain.getPosition(),
                domain.getWeeklyHours(),
                domain.getSalary(),
                domain.getStatus()
        );
    }

    public TeacherContract toTeacherContract(CreateTeacherContractRequest request) {
        return TeacherContract.builder()
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