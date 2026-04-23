package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.UserStory;
import com.colegio.backend.infrastructure.persistence.entity.UserStoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserStoryMapper {

    UserStory toDomain(UserStoryEntity entity);

    UserStoryEntity toEntity(UserStory userStory);
}