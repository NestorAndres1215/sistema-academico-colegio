package com.colegio.backend.modules.teacher.infrastructure.persistence.mapper;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;

    public Teacher toDomain(TeacherEntity entity) {

        return Teacher.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .user(userMapperPersistence.toDomain(entity.getUser()))
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .dni(entity.getDni())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .maritalStatus(entity.getMaritalStatus())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .specialty(entity.getSpecialty())
                .academicDegree(entity.getAcademicDegree())
                .professionalLicenseNumber(entity.getProfessionalLicenseNumber())
                .photo(entity.getPhoto())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public TeacherEntity toEntity(Teacher domain) {

        return TeacherEntity.builder()
                .id(domain.getId())
                .code(domain.getCode())
                .user(userMapperPersistence.toEntity(domain.getUser()))
                .firstName(domain.getFirstName())
                .middleName(domain.getMiddleName())
                .paternalLastName(domain.getPaternalLastName())
                .maternalLastName(domain.getMaternalLastName())
                .dni(domain.getDni())
                .birthDate(domain.getBirthDate())
                .gender(domain.getGender())
                .maritalStatus(domain.getMaritalStatus())
                .phone(domain.getPhone())
                .address(domain.getAddress())
                .specialty(domain.getSpecialty())
                .academicDegree(domain.getAcademicDegree())
                .professionalLicenseNumber(domain.getProfessionalLicenseNumber())
                .photo(domain.getPhoto())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
