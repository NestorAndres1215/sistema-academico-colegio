package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.UserStory;

import com.colegio.backend.infrastructure.persistence.entity.UserStoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoryMapper {

    private final UserMapper userMapper;

    public UserStory toDomain(UserStoryEntity entity) {
        if (entity == null) return null;

        return UserStory.builder()
                .id(entity.getId())
                .action(entity.getAction())
                .detail(entity.getDetail())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .user(userMapper.toDomain(entity.getUserEntity()))
                .build();
    }

    public UserStoryEntity toEntity(UserStory domain) {
        if (domain == null) return null;

        return UserStoryEntity.builder()
                .id(domain.getId())
                .action(domain.getAction())
                .detail(domain.getDetail())
                .createdAt(domain.getCreatedAt())
                .status(domain.getStatus())
                .userEntity(userMapper.toEntity(domain.getUser()))
                .build();
    }
}