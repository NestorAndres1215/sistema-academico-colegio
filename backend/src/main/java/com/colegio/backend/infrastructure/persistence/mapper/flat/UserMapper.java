package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.User;
import com.colegio.backend.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}