package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import java.util.Optional;

public interface TeacherContractRepositoryPort {

    Optional<TeacherContract> findByTeacher_Id(Long id);

    Optional<TeacherContract> findById(Long id);

    TeacherContract save (TeacherContract teacherContract);

}
