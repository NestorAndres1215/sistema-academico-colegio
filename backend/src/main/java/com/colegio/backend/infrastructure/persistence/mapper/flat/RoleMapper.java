package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.Role;
import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toDomain(RoleEntity entity);

    RoleEntity toEntity(Role role);

    List<Role> toDomainList(List<RoleEntity> entities);
}