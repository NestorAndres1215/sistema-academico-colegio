package com.colegio.backend.application.dto.company;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyRequest {

    private String name;
    private String logo;
    private String ruc;
    private String description;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String country;
    private String companyType;
    private LocalDate foundingDate;
}
