package com.colegio.backend.modules.user_history.infrastructure.persistence.mapper;

import com.colegio.backend.modules.user.infrastructure.persistence.mapper.UserMapperPersistence;
import com.colegio.backend.modules.user_history.domain.model.UserHistory;
import com.colegio.backend.modules.user_history.infrastructure.persistence.entity.UserHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHistoryMapperPersistence {

    private final UserMapperPersistence userMapperPersistence;

    public UserHistory toDomain(UserHistoryEntity entity) {
        if (entity == null) return null;

        return UserHistory.builder()
                .id(entity.getId())
                .action(entity.getAction())
                .module(entity.getModule())
                .detail(entity.getDetail())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .user(userMapperPersistence.toDomain(entity.getUser()))
                .build();
    }

    public UserHistoryEntity toEntity(UserHistory domain) {
        if (domain == null) return null;

        return UserHistoryEntity.builder()
                .id(domain.getId())
                .action(domain.getAction())
                .module(domain.getModule())
                .detail(domain.getDetail())
                .createdAt(domain.getCreatedAt())
                .status(domain.getStatus())
                .user(userMapperPersistence.toEntity(domain.getUser()))
                .build();
    }

}
