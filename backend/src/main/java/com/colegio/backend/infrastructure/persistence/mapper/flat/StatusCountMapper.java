package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.application.dto.statistics.StatusCountResponse;
import com.colegio.backend.infrastructure.persistence.projection.StatusCount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusCountMapper {

    StatusCountResponse toResponse(StatusCount projection);
}