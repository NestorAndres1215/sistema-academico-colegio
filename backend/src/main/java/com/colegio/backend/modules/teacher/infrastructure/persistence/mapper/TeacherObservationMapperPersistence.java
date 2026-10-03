package com.colegio.backend.modules.teacher.infrastructure.persistence.mapper;

import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherObservationEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherObservationMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;
    private final TeacherMapperPersistence teacherMapperPersistence;

    public TeacherObservation toDomain(TeacherObservationEntity entity) {

        if (entity == null) {
            return null;
        }

        return TeacherObservation.builder()
                .id(entity.getId())
                .teacher( teacherMapperPersistence.toDomain(entity.getTeacher()))
                .observer(userMapperPersistence.toDomain(entity.getObserver()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .observationDate(entity.getObservationDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TeacherObservationEntity toEntity(TeacherObservation domain) {
        if (domain == null) {
            return null;
        }
        return TeacherObservationEntity.builder()
                .id(domain.getId())
                .teacher(teacherMapperPersistence.toEntity(domain.getTeacher()))
                .observer(userMapperPersistence.toEntity(domain.getObserver()))
                .title(domain.getTitle())
                .description(domain.getDescription())
                .observationDate(domain.getObservationDate())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
