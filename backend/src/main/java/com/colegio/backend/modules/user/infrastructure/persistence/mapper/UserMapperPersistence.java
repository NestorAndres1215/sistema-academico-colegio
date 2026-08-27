package com.colegio.backend.modules.user.infrastructure.persistence.mapper;


import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.RoleEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.RoleMapperPersistence;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapperPersistence {

    private final RoleMapperPersistence roleMapper;

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .roles(toDomainRoles(entity.getRoleEntities()))
                .build();
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        return UserEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .roleEntities(toEntityRoles(domain.getRoles()))
                .build();
    }

    private List<Role> toDomainRoles(List<RoleEntity> roles) {
        return roles.stream()
                .map(roleMapper::toDomain)
                .toList();
    }

    private List<RoleEntity> toEntityRoles(List<Role> roles) {
        return roles.stream()
                .map(roleMapper::toEntity)
                .toList();
    }

}