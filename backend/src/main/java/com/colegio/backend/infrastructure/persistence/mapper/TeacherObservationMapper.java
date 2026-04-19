package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.TeacherObservation;
import com.colegio.backend.infrastructure.persistence.entity.TeacherObservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherObservationMapper {

    private final TeacherMapper teacherMapper;
    private final AdministratorMapper administratorMapper;

    public TeacherObservation toDomain(TeacherObservationEntity entity) {
        if (entity == null) return null;

        return TeacherObservation.builder()
                .id(entity.getId())
                .teacher(teacherMapper.toDomain(entity.getTeacher()))
                .admin(administratorMapper.toDomain(entity.getAdmin()))
                .observation(entity.getObservation())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TeacherObservationEntity toEntity(TeacherObservation teacherObservation) {
        if (teacherObservation == null) return null;

        return TeacherObservationEntity.builder()
                .id(teacherObservation.getId())
                .teacher(teacherMapper.toEntity(teacherObservation.getTeacher()))
                .admin(administratorMapper.toEntity(teacherObservation.getAdmin()))
                .observation(teacherObservation.getObservation())
                .status(teacherObservation.getStatus())
                .createdAt(teacherObservation.getCreatedAt())
                .updatedAt(teacherObservation.getUpdatedAt())
                .build();
    }

}
