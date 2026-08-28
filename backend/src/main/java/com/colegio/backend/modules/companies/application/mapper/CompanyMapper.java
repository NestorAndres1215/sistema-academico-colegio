package com.colegio.backend.modules.companies.application.mapper;

import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.domain.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        if (company == null) {
            return null;
        }

        return CompanyResponse.builder()
                .id(company.getId())
                .code(company.getCode())
                .name(company.getName())
                .businessName(company.getBusinessName())
                .country(company.getCountry())
                .city(company.getCity())
                .logoUrl(company.getLogoUrl())
                .website(company.getWebsite())
                .status(company.getStatus())
                .build();
    }
}