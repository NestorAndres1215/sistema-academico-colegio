package com.colegio.backend.modules.companies.application.validator;

import com.colegio.backend.modules.companies.application.dto.CompanyRequest;
import com.colegio.backend.modules.companies.domain.model.Company;

import com.colegio.backend.modules.companies.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.shared.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyValidator {

    private final CompanyRepositoryPort companyRepositoryPort;

    public void validateForCreate(CompanyRequest request) {

        if (companyRepositoryPort.existsByEmail(request.email())) {
            throw new ConflictException("El correo electrónico ya está registrado");
        }

        if (companyRepositoryPort.existsByCode(request.code())) {
            throw new ConflictException("El código ya está registrado");
        }

        if (companyRepositoryPort.existsByTaxId(request.taxId())) {
            throw new ConflictException("El RUC ya está registrado");
        }
    }

    public void validateForUpdate(Company existing, CompanyRequest request) {

        if (!existing.getEmail().equals(request.email()) && companyRepositoryPort.existsByEmail(request.email())) {
            throw new ConflictException("El correo electrónico ya está registrado");
        }

        if (!existing.getCode().equals(request.code()) && companyRepositoryPort.existsByCode(request.code())) {
            throw new ConflictException("El código ya está registrado");
        }

        if (!existing.getTaxId().equals(request.taxId()) && companyRepositoryPort.existsByTaxId(request.taxId())) {
            throw new ConflictException("El RUC ya está registrado");
        }
    }
}