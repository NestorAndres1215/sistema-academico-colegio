package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Teacher;
import com.colegio.backend.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.infrastructure.persistence.mapper.flat.TeacherMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeacherRepositoryAdapter  implements TeacherRepositoryPort {

    private final JpaTeacherRepository repository;
    private final TeacherMapper mapper;

    @Override
    public Optional<Teacher> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Teacher> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Teacher save(Teacher teacher) {
        TeacherEntity entity = mapper.toEntity(teacher);
        TeacherEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public String findLastCode() {
        return repository.findLastCode();
    }

    @Override
    public boolean existsByDni(String dni) {
        return repository.existsByDni(dni);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    @Override
    public Page<Teacher> getByStatus(Status status, String search, Pageable pageable) {
        return repository.searchByStatus(status, search, pageable).map(mapper::toDomain);
    }
}
