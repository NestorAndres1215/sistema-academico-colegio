package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Company;
import com.colegio.backend.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toDomain(CompanyEntity entity) {
        if (entity == null) return null;

        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .logo(entity.getLogo())
                .ruc(entity.getRuc())
                .description(entity.getDescription())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .city(entity.getCity())
                .country(entity.getCountry())
                .companyType(entity.getCompanyType())
                .foundingDate(entity.getFoundingDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CompanyEntity toEntity(Company company) {
        if (company == null) return null;

        return CompanyEntity.builder()
                .id(company.getId())
                .name(company.getName())
                .logo(company.getLogo())
                .ruc(company.getRuc())
                .description(company.getDescription())
                .phone(company.getPhone())
                .email(company.getEmail())
                .address(company.getAddress())
                .city(company.getCity())
                .country(company.getCountry())
                .companyType(company.getCompanyType())
                .foundingDate(company.getFoundingDate())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}
