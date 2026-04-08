package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.model.Company;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface CompanyUseCase {

    List<Company> findAll();

    Company findByName(String name);

    Company findById(String id);

    Company save(MultipartFile logo,CompanyRequest companyRequest) throws IOException;

    Company update (String id,CompanyRequest companyRequest);

}
