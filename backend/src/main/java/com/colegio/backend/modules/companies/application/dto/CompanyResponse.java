package com.colegio.backend.modules.companies.application.dto;

public record CompanyResponse(
        Long id,
        String code,
        String name,
        String businessName,
        String country,
        String city,
        String logoUrl,
        String website,
        String status
) {
}