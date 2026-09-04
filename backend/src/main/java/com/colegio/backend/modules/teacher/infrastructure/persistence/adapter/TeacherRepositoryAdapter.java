package com.colegio.backend.modules.teacher.infrastructure.persistence.adapter;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter implements TeacherRepositoryPort {

    private final JpaTeacherRepository jpaTeacherRepository;
    private final TeacherMapperPersistence teacherMapperPersistence;

    @Override
    public Optional<Teacher> findById(Long id) {
        return jpaTeacherRepository.findById(id)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Optional<Teacher> findByCode(String code) {
        return jpaTeacherRepository.findByCode(code)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Optional<Teacher> findByDni(String dni) {
        return jpaTeacherRepository.findByDni(dni)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Optional<Teacher> findByProfessionalLicenseNumber(String professionalLicenseNumber) {
        return jpaTeacherRepository.findByProfessionalLicenseNumber(professionalLicenseNumber)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Optional<Teacher> findByUser_Email(String email) {
        return jpaTeacherRepository.findByUser_Email(email)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Optional<Teacher> findByUser_Username(String username) {
        return jpaTeacherRepository.findByUser_Username(username)
                .map(teacherMapperPersistence::toDomain);
    }

    @Override
    public Teacher save(Teacher teacher) {

        TeacherEntity entity = teacherMapperPersistence.toEntity(teacher);

        TeacherEntity saved = jpaTeacherRepository.save(entity);

        return teacherMapperPersistence.toDomain(saved);
    }

    @Override
    public boolean existsByProfessionalLicenseNumber(String professionalLicenseNumber) {
        return jpaTeacherRepository.existsByProfessionalLicenseNumber(professionalLicenseNumber);
    }

    @Override
    public boolean existsByDni(String dni) {
        return jpaTeacherRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return jpaTeacherRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaTeacherRepository.existsByCode(code);
    }
}
