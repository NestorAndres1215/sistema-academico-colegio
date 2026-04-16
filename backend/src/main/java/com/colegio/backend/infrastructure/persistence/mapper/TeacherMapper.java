package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Teacher;
import com.colegio.backend.infrastructure.persistence.entity.TeacherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final UserMapper userMapper;

    public Teacher toDomain(TeacherEntity entity) {
        if (entity == null) return null;

        return Teacher.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .age(entity.getAge())
                .dni(entity.getDni())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .profile(entity.getProfile())
                .gender(entity.getGender())
                .nationality(entity.getNationality())
                .active(entity.getActive())
                .specialization(entity.getSpecialization())
                .status(entity.getStatus())
                .hireDate(entity.getHireDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .user(userMapper.toDomain(entity.getUserEntity()))
                .build();
    }

    public TeacherEntity toEntity(Teacher dto) {
        if (dto == null) return null;

        return TeacherEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .paternalLastName(dto.getPaternalLastName())
                .maternalLastName(dto.getMaternalLastName())
                .age(dto.getAge())
                .dni(dto.getDni())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .profile(dto.getProfile())
                .gender(dto.getGender())
                .nationality(dto.getNationality())
                .active(dto.getActive())
                .specialization(dto.getSpecialization())
                .status(dto.getStatus())
                .hireDate(dto.getHireDate())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .userEntity(userMapper.toEntity(dto.getUser()))
                .build();
    }
}