package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.domain.port.usecases.CompanyUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyUseCase {

   private final CompanyRepositoryPort repositoryPort;

    @Override
    public List<Company> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Company findByName(String name) {
        return repositoryPort.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    @Override
    public Company findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    @Override
    public Company save(CompanyRequest companyRequest) {
        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());
        Company company = Company.builder()
                .id(newCode)
                .name(companyRequest.getName())
                .logo(companyRequest.getLogo())
                .ruc(companyRequest.getRuc())
                .description(companyRequest.getDescription())
                .phone(companyRequest.getPhone())
                .email(companyRequest.getEmail())
                .address(companyRequest.getAddress())
                .city(companyRequest.getCity())
                .country(companyRequest.getCountry())
                .companyType(companyRequest.getCompanyType())
                .foundingDate(companyRequest.getFoundingDate())
                .createdAt(LocalDateTime.now())
                .build();

        return  repositoryPort.save(company);
    }

    @Override
    public Company update(String id, CompanyRequest companyRequest) {

        Company existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        existing.setName(companyRequest.getName());
        existing.setLogo(companyRequest.getLogo());
        existing.setRuc(companyRequest.getRuc());
        existing.setDescription(companyRequest.getDescription());
        existing.setPhone(companyRequest.getPhone());
        existing.setEmail(companyRequest.getEmail());
        existing.setAddress(companyRequest.getAddress());
        existing.setCity(companyRequest.getCity());
        existing.setCountry(companyRequest.getCountry());
        existing.setCompanyType(companyRequest.getCompanyType());
        existing.setFoundingDate(companyRequest.getFoundingDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return repositoryPort.save(existing);
    }


}
