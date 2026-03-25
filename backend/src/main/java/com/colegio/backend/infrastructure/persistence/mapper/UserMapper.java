package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.User;
import com.colegio.backend.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .roles(roleMapper.toDomainList(entity.getRoles()))
                .build();
    }

    public UserEntity toEntity(User user) {
        if (user == null) return null;

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles() != null ? user.getRoles().stream()
                        .map(roleMapper::toEntity)
                        .toList() : List.of())
                .build();
    }
}