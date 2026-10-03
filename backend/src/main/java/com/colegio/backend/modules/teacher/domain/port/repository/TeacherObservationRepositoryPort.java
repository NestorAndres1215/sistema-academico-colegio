package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface TeacherObservationRepositoryPort {

    Optional<TeacherObservation> findByTeacher_Id(Long id);

    Optional<TeacherObservation> findById(Long id);

    TeacherObservation save (TeacherObservation teacherDetails);

    Page<TeacherObservation> findByFilters(String teacherCode, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
