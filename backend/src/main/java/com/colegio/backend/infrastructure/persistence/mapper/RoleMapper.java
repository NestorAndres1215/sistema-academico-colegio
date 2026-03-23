package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Role;
import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RoleMapper {


    public Role toDomain(RoleEntity entity) {
        if (entity == null) return null;

        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public List<Role> toDomainList(List<RoleEntity> entities) {
        if (entities == null) return List.of();

        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) return null;

        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}