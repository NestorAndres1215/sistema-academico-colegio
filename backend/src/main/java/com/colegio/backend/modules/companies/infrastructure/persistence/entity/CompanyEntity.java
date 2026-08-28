package com.colegio.backend.modules.companies.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( unique = true, nullable = false)
    private String code;

    private String name;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "tax_id", unique = true)
    private String taxId;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String address;

    private String city;

    private String country;

    @Column(name = "logo_url")
    private String logoUrl;

    private String website;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
