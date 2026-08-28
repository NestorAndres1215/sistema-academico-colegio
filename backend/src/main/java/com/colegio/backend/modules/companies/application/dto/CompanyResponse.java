package com.colegio.backend.modules.companies.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

    private Long id;
    private String code;
    private String name;
    private String businessName;
    private String country;
    private String city;
    private String logoUrl;
    private String website;
    private String status;
}
