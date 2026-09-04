package com.colegio.backend.modules.teacher.infrastructure.persistence.adapter;

import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherContractRepositoryPort;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherContractEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherContractMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeacherContractRepositoryAdapter implements TeacherContractRepositoryPort {

    private final JpaTeacherContractRepository jpaTeacherContractRepository;
    private final TeacherContractMapperPersistence teacherContractMapperPersistence;


    @Override
    public Optional<TeacherContract> findById(Long id) {
        return jpaTeacherContractRepository.findById(id)
                .map(teacherContractMapperPersistence::toDomain);
    }

    @Override
    public TeacherContract save(TeacherContract teacherContract) {

        TeacherContractEntity entity = teacherContractMapperPersistence.toEntity(teacherContract);

        TeacherContractEntity saved = jpaTeacherContractRepository.save(entity);

        return teacherContractMapperPersistence.toDomain(saved);
    }
}
