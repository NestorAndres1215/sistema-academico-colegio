package com.colegio.backend.modules.teacher.infrastructure.persistence.adapter;

import com.colegio.backend.modules.teacher.domain.model.TeacherObservation;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherObservationRepositoryPort;
import com.colegio.backend.modules.teacher.infrastructure.persistence.mapper.TeacherObservationMapperPersistence;
import com.colegio.backend.modules.teacher.infrastructure.persistence.repository.JpaTeacherObservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeacherObservationRepositoryAdapter  implements TeacherObservationRepositoryPort {

   private final TeacherObservationMapperPersistence teacherObservationMapperPersistence;
   private final JpaTeacherObservationRepository jpaTeacherObservationRepository;


    @Override
    public Optional<TeacherObservation> findByTeacher_Id(Long id) {
        return jpaTeacherObservationRepository.findByTeacher_Id(id)
                .map(teacherObservationMapperPersistence::toDomain);
    }

    @Override
    public Optional<TeacherObservation> findById(Long id) {
        return jpaTeacherObservationRepository.findById(id)
                .map(teacherObservationMapperPersistence::toDomain);
    }

    @Override
    public TeacherObservation save(TeacherObservation teacherObservation) {
        var entity = teacherObservationMapperPersistence.toEntity(teacherObservation);

        var savedEntity = jpaTeacherObservationRepository.save(entity);

        return teacherObservationMapperPersistence.toDomain(savedEntity);
    }

    @Override
    public Page<TeacherObservation> findByFilters(
            String teacherCode,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return jpaTeacherObservationRepository
                .findAllByFilters(teacherCode, startDate, endDate, pageable)
                .map(teacherObservationMapperPersistence::toDomain);
    }
}
