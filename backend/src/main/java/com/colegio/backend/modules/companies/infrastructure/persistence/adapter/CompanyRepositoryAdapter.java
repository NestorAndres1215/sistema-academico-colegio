package com.colegio.backend.modules.companies.infrastructure.persistence.adapter;

import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.companies.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.modules.companies.infrastructure.persistence.entity.CompanyEntity;
import com.colegio.backend.modules.companies.infrastructure.persistence.mapper.CompanyMapperPersistence;
import com.colegio.backend.modules.companies.infrastructure.persistence.repository.JpaCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {

    private final JpaCompanyRepository jpaCompanyRepository;
    private final CompanyMapperPersistence companyMapperPersistence;

    @Override
    public Page<Company> getByStatus(String status, String search, Pageable pageable) {
        return jpaCompanyRepository.searchByStatus(status, search, pageable)
                .map(companyMapperPersistence::toDomain);
    }
    @Override
    public Optional<Company> findByEmail(String email) {
        return jpaCompanyRepository.findByEmail(email)
                .map(companyMapperPersistence::toDomain);
    }

    @Override
    public Optional<Company> findByCode(String code) {
        return jpaCompanyRepository.findByCode(code)
                .map(companyMapperPersistence::toDomain);
    }

    @Override
    public List<Company> findByStatus(String status) {
        return jpaCompanyRepository.findByStatus(status)
                .stream()
                .map(companyMapperPersistence::toDomain)
                .toList();
    }

    @Override
    public List<Company> findAll() {
        return jpaCompanyRepository.findAll()
                .stream()
                .map(companyMapperPersistence::toDomain)
                .toList();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return jpaCompanyRepository.findById(id)
                .map(companyMapperPersistence::toDomain);
    }

    @Override
    public Company save(Company company) {
        CompanyEntity entity = companyMapperPersistence.toEntity(company);
        CompanyEntity savedEntity = jpaCompanyRepository.save(entity);
        return companyMapperPersistence.toDomain(savedEntity);
    }
}
