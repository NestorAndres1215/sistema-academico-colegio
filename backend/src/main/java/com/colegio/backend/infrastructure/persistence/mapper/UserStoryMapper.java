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

    public UserStoryEntity toEntity(UserStory userStory) {
        if (userStory == null) return null;

        return UserStoryEntity.builder()
                .id(userStory.getId())
                .action(userStory.getAction())
                .detail(userStory.getDetail())
                .createdAt(userStory.getCreatedAt())
                .status(userStory.getStatus())
                .userEntity(userMapper.toEntity(userStory.getUser()))
                .build();
    }
}