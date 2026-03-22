package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {


    public String toDomain(RoleEntity entity) {
        if (entity == null) return null;
        return entity.getName();
    }

    public List<String> toDomainList(List<RoleEntity> entities) {
        if (entities == null) return List.of();

        return entities.stream()
                .map(RoleEntity::getName)
                .toList();
    }


}