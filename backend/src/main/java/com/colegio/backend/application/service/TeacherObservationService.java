package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.teacher.TeacherObservationRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.TeacherObservation;
import com.colegio.backend.domain.port.repository.TeacherObservationRepositoryPort;
import com.colegio.backend.domain.port.usecases.TeacherObservationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherObservationService implements TeacherObservationUseCase {

    private final TeacherObservationRepositoryPort repositoryPort;

    @Override
    public TeacherObservation findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }

    @Override
    public List<TeacherObservation> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public TeacherObservation save(TeacherObservationRequest teacherObservationRequest) {
        return null;
    }

    @Override
    public TeacherObservation update(String id, TeacherObservationRequest teacherObservationRequest) {
        return null;
    }

    @Override
    public Page<TeacherObservation> findByTeacherAndStatus(String teacherId, Status status, Pageable pageable) {
        return repositoryPort.findByTeacherAndStatus(teacherId,status,pageable);
    }

    @Override
    public TeacherObservation deactivate(String id) {
        TeacherObservation existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        existing.setStatus(Status.INACTIVE);

        return repositoryPort.save(existing);
    }

    @Override
    public TeacherObservation activate(String id) {
        TeacherObservation existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        existing.setStatus(Status.ACTIVE);

        return repositoryPort.save(existing);
    }
}
