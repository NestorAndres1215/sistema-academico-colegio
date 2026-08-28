package com.colegio.backend.modules.companies.application.dto;



public record CompanyResponse(
        Long id,
        String code,
        String name,
        String businessName,
        String taxId,
        String email,
        String phone,
        String address,
        String city,
        String country,
        String logoUrl,
        String website
) {
}