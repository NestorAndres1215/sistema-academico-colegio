package com.colegio.backend.modules.teacher.infrastructure.persistence.mapper;

import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherContractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherContractMapperPersistence {

    private final TeacherMapperPersistence teacherMapperPersistence;

    public TeacherContract toDomain(TeacherContractEntity entity) {

        return TeacherContract.builder()
                .id(entity.getId())
                .teacher(teacherMapperPersistence.toDomain(entity.getTeacher()))
                .contractType(entity.getContractType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .position(entity.getPosition())
                .weeklyHours(entity.getWeeklyHours())
                .salary(entity.getSalary())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TeacherContractEntity toEntity(TeacherContract domain) {

        return TeacherContractEntity.builder()
                .id(domain.getId())
                .teacher(teacherMapperPersistence.toEntity(domain.getTeacher()))
                .contractType(domain.getContractType())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .position(domain.getPosition())
                .weeklyHours(domain.getWeeklyHours())
                .salary(domain.getSalary())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}