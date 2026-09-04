package com.colegio.backend.modules.teacher.infrastructure.persistence.mapper;

import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherDetailsMapperPersistence {

    private final TeacherMapperPersistence teacherMapperPersistence;

    public TeacherDetails toDomain(TeacherDetailsEntity entity) {

        return TeacherDetails.builder()
                .id(entity.getId())
                .teacher(teacherMapperPersistence.toDomain(entity.getTeacher()))
                .university(entity.getUniversity())
                .graduationDate(entity.getGraduationDate())
                .yearsOfExperience(entity.getYearsOfExperience())
                .curriculum(entity.getCurriculum())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TeacherDetailsEntity toEntity(TeacherDetails domain) {

        return TeacherDetailsEntity.builder()
                .id(domain.getId())
                .teacher(teacherMapperPersistence.toEntity(domain.getTeacher()))
                .university(domain.getUniversity())
                .graduationDate(domain.getGraduationDate())
                .yearsOfExperience(domain.getYearsOfExperience())
                .curriculum(domain.getCurriculum())
                .notes(domain.getNotes())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

}
