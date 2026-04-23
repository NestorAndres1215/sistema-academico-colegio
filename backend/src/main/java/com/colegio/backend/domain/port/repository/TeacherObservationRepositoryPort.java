package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.TeacherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TeacherObservationRepositoryPort {

    Optional<TeacherObservation> findById(String id);

    List<TeacherObservation> findAll();

    String findLastCode();

    Page<TeacherObservation> findByTeacherAndStatus(String teacherId, Status status, Pageable pageable);

    TeacherObservation save(TeacherObservation teacherObservation);
}
