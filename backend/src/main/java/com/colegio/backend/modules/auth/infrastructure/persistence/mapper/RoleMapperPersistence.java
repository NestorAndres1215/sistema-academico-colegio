package com.colegio.backend.modules.auth.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperPersistence {

    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }

        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public RoleEntity toEntity(Role domain) {
        if (domain == null) {
            return null;
        }

        return RoleEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}