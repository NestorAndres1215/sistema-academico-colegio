package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.TeacherObservation;
import com.colegio.backend.infrastructure.persistence.entity.TeacherObservationEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        TeacherMapper.class,
        AdministratorMapper.class
})
public interface TeacherObservationMapper {

    TeacherObservation toDomain(TeacherObservationEntity entity);

    TeacherObservationEntity toEntity(TeacherObservation domain);
}