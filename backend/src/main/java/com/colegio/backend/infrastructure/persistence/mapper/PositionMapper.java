package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Position;
import com.colegio.backend.infrastructure.persistence.entity.PositionsEntity;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

    public Position toDomain(PositionsEntity entity) {
        if (entity == null) return null;
        return Position.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public PositionsEntity toEntity(Position dto) {
        if (dto == null) return null;
        return PositionsEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}