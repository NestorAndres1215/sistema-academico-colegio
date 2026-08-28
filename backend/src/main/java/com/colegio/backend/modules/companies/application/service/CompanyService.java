package com.colegio.backend.modules.companies.application.service;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.application.mapper.CompanyMapper;
import com.colegio.backend.modules.companies.application.validator.CompanyValidator;
import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.companies.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.modules.companies.domain.port.usecase.CompanyUseCase;
import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyUseCase {

    private final CompanyRepositoryPort companyRepositoryPort;
    private final CompanyMapper companyMapper;
    private final CompanyValidator companyValidator;
    private final FileUseCase fileUseCase;


    @Override
    public Page<CompanyResponse> getByStatus(String status, String search, Pageable pageable) {
        return companyRepositoryPort
                .getByStatus(status, search,pageable)
                .map(companyMapper::toResponse);
    }

    @Override
    public CompanyResponse findById(Long id) {
        return companyMapper.toResponse(findCompanyById(id));
    }

    @Override
    public CompanyResponse findByCode(String code) {
        return companyMapper.toResponse(findCompanyByCode(code));
    }

    @Override
    public Company save(MultipartFile logo,CompanyRequest companyRequest) throws IOException {
        companyValidator.validateForCreate(companyRequest);

        Company company = companyMapper.toDomain(companyRequest);

        if (logo != null && !logo.isEmpty()) {
            String fileUrl = fileUseCase.storeFile(logo, "company");
            company.setLogoUrl(fileUrl);
        }

        return companyRepositoryPort.save(company);
    }

    @Override
    public Company update(Long id, MultipartFile logo, CompanyRequest companyRequest) throws IOException {

        Company existing = findCompanyById(id);

        companyValidator.validateForUpdate(existing, companyRequest);
        companyMapper.updateDomain(existing, companyRequest);

        if (logo != null && !logo.isEmpty()) {

            String oldLogoUrl = existing.getLogoUrl();
            String fileUrl = fileUseCase.storeFile(logo, "company");
            existing.setLogoUrl(fileUrl);

            if (oldLogoUrl != null && !oldLogoUrl.isBlank()) {
                fileUseCase.deleteFile(oldLogoUrl);
            }
        }

        existing.setUpdatedAt(LocalDateTime.now());

        return companyRepositoryPort.save(existing);
    }

    private Company findCompanyById(Long id) {
        return companyRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    private Company findCompanyByCode(String code) {
        return companyRepositoryPort.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }
}
