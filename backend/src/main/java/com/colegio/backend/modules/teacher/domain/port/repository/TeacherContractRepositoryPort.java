package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherContractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TeacherContractRepositoryPort {

    Optional<TeacherContract> findByTeacher_Id(Long id);

    Optional<TeacherContract> findById(Long id);

    TeacherContract save (TeacherContract teacherContract);

    Page<TeacherContract> findWithFilters(
            String teacherCode,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    Optional<TeacherContract> findByIdWithTeacher(Long id);

}
