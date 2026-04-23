package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.TeacherObservation;
import com.colegio.backend.domain.port.repository.TeacherObservationRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.TeacherObservationEntity;
import com.colegio.backend.infrastructure.persistence.mapper.flat.TeacherObservationMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaTeacherObservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeacherObservationRepositoryAdapter implements TeacherObservationRepositoryPort {

    private final JpaTeacherObservationRepository repository;
    private final TeacherObservationMapper mapper;

    @Override
    public Optional<TeacherObservation> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TeacherObservation> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public String findLastCode() {
        return repository.findLastCode();
    }

    @Override
    public Page<TeacherObservation> findByTeacherAndStatus(String teacherId, Status status, Pageable pageable) {
        return repository.findByTeacherAndStatus(teacherId, status, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public TeacherObservation save(TeacherObservation teacherObservation) {
        TeacherObservationEntity entity = mapper.toEntity(teacherObservation);
        TeacherObservationEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}
