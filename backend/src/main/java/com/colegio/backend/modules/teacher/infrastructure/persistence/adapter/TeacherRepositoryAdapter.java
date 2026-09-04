package com.colegio.backend.modules.teacher.infrastructure.persistence.adapter;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Teacher> findByAllStatus(String status, String search, Pageable pageable) {
        return jpaTeacherRepository.findByAllStatus(status,search ,pageable)
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
