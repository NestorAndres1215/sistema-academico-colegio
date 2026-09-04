package com.colegio.backend.modules.teacher.infrastructure.persistence.adapter;

import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherDetailsRepositoryPort;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherDetailsMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherDetailsRepository;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class TeacherDetailsRepositoryAdapter implements TeacherDetailsRepositoryPort {

    private final JpaTeacherDetailsRepository jpaTeacherDetailsRepository;
    private final TeacherDetailsMapperPersistence teacherDetailsMapperPersistence;


    @Override
    public Optional<TeacherDetails> findById(Long id) {
        return jpaTeacherDetailsRepository.findById(id)
                .map(teacherDetailsMapperPersistence::toDomain);
    }

    @Override
    public TeacherDetails save(TeacherDetails teacherDetails) {

        TeacherDetailsEntity entity = teacherDetailsMapperPersistence.toEntity(teacherDetails);

        TeacherDetailsEntity saved = jpaTeacherDetailsRepository.save(entity);

        return teacherDetailsMapperPersistence.toDomain(saved);
    }
}
