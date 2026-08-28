package com.colegio.backend.modules.companies.domain.port.usecase;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CompanyUseCase {

    Page<CompanyResponse> getByStatus(String status, String search, Pageable pageable);

    CompanyResponse findById(Long id);

    CompanyResponse findByCode(String code);

    Company save(MultipartFile logo, CompanyRequest companyRequest) throws IOException;

    Company update (Long id, MultipartFile logo, CompanyRequest companyRequest) throws IOException;

}
