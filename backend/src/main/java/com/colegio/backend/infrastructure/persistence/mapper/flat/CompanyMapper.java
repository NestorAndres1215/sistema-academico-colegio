package com.colegio.backend.infrastructure.persistence.mapper.flat;

import com.colegio.backend.domain.model.Company;
import com.colegio.backend.infrastructure.persistence.entity.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toDomain(CompanyEntity entity);

    CompanyEntity toEntity(Company company);
}