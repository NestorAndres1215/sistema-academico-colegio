package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.Position;
import com.colegio.backend.infrastructure.persistence.entity.PositionsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position toDomain(PositionsEntity entity);

    PositionsEntity toEntity(Position position);
}