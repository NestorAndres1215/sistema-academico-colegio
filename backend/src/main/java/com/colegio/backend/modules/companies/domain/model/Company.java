package com.colegio.backend.modules.companies.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class Company {

    private Long id;
    private String code;
    private String name;
    private String businessName;
    private String taxId;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String logoUrl;
    private String website;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
