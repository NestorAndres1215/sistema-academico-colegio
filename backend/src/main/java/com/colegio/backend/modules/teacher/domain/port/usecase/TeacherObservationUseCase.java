package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.application.dto.teacher_observation.CreateTeacherObservationRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_observation.TeacherObservationResponse;
import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;


public interface TeacherObservationUseCase {

    TeacherObservationResponse findByTeacher_Id(Long id);

    TeacherObservationResponse findById(Long id);

    TeacherObservation save (CreateTeacherObservationRequest createTeacherObservationRequest);

    Page<TeacherObservationResponse> findByFilters(String teacherCode, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
