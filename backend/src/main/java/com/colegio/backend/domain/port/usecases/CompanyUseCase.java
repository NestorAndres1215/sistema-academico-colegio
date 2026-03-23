package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.model.Company;

import java.util.List;


public interface CompanyUseCase {

    List<Company> findAll();

     Company findByName(String name);

     Company findById(String id);

    Company save(CompanyRequest companyRequest);

    Company update (String id,CompanyRequest companyRequest);

}
