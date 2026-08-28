package com.colegio.backend.modules.companies.application.mapper;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.domain.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        if (company == null) {
            return null;
        }

        return new CompanyResponse(
                company.getId(),
                company.getCode(),
                company.getName(),
                company.getBusinessName(),
                company.getCountry(),
                company.getCity(),
                company.getLogoUrl(),
                company.getWebsite(),
                company.getStatus()
        );
    }

    public Company toDomain(CompanyRequest request) {
        if (request == null) {
            return null;
        }

        return Company.builder()
                .code(request.code())
                .name(request.name())
                .businessName(request.businessName())
                .taxId(request.taxId())
                .email(request.email())
                .phone(request.phone())
                .address(request.address())
                .city(request.city())
                .country(request.country())
                .website(request.website())
                .build();
    }

    public void updateDomain(Company company, CompanyRequest request) {

        company.setCode(request.code());
        company.setName(request.name());
        company.setBusinessName(request.businessName());
        company.setTaxId(request.taxId());
        company.setEmail(request.email());
        company.setPhone(request.phone());
        company.setAddress(request.address());
        company.setCity(request.city());
        company.setCountry(request.country());
        company.setWebsite(request.website());
    }

}