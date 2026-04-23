package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.Teacher;
import com.colegio.backend.infrastructure.persistence.entity.TeacherEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TeacherMapper {

    Teacher toDomain(TeacherEntity entity);

    TeacherEntity toEntity(Teacher teacher);
}