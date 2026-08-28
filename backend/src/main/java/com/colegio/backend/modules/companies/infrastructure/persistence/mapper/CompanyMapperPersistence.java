package com.colegio.backend.modules.companies.infrastructure.persistence.mapper;

import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.companies.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperPersistence {

    public Company toDomain(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }

        return Company.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .businessName(entity.getBusinessName())
                .taxId(entity.getTaxId())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .city(entity.getCity())
                .country(entity.getCountry())
                .logoUrl(entity.getLogoUrl())
                .website(entity.getWebsite())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CompanyEntity toEntity(Company company) {
        if (company == null) {
            return null;
        }

        return CompanyEntity.builder()
                .id(company.getId())
                .code(company.getCode())
                .name(company.getName())
                .businessName(company.getBusinessName())
                .taxId(company.getTaxId())
                .email(company.getEmail())
                .phone(company.getPhone())
                .address(company.getAddress())
                .city(company.getCity())
                .country(company.getCountry())
                .logoUrl(company.getLogoUrl())
                .website(company.getWebsite())
                .status(company.getStatus())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}