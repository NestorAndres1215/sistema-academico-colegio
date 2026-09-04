package com.colegio.backend.modules.teacher.domain.port.repository;


import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;

import java.util.Optional;

public interface TeacherDetailsRepositoryPort {

    Optional<TeacherDetails> findById(Long id);

    TeacherDetails save (TeacherDetails teacherDetails);
}
