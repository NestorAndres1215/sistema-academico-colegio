package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AdministratorMapper {

    @Mapping(source = "userEntity", target = "user")
    Administrator toDomain(AdministratorEntity entity);

    @Mapping(source = "user", target = "userEntity")
    AdministratorEntity toEntity(Administrator administrator);
}