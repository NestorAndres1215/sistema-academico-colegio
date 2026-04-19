package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.teacher.TeacherObservationRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.TeacherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TeacherObservationUseCase {

    TeacherObservation findById(String id);

    List<TeacherObservation> findAll();

    TeacherObservation save(TeacherObservationRequest teacherObservationRequest);

    TeacherObservation update(String id, TeacherObservationRequest teacherObservationRequest);

    Page<TeacherObservation> findByTeacherAndStatus(String teacherId, Status status, Pageable pageable);

    TeacherObservation deactivate(String id);

    TeacherObservation activate(String id);

}
