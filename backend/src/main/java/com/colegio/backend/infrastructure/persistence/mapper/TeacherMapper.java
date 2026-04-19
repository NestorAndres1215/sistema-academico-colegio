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

    public TeacherEntity toEntity(Teacher teacher) {
        if (teacher == null) return null;

        return TeacherEntity.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .middleName(teacher.getMiddleName())
                .paternalLastName(teacher.getPaternalLastName())
                .maternalLastName(teacher.getMaternalLastName())
                .age(teacher.getAge())
                .dni(teacher.getDni())
                .phone(teacher.getPhone())
                .birthDate(teacher.getBirthDate())
                .profile(teacher.getProfile())
                .gender(teacher.getGender())
                .nationality(teacher.getNationality())
                .active(teacher.getActive())
                .specialization(teacher.getSpecialization())
                .status(teacher.getStatus())
                .hireDate(teacher.getHireDate())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .userEntity(userMapper.toEntity(teacher.getUser()))
                .build();
    }
}