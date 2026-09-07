package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

}
