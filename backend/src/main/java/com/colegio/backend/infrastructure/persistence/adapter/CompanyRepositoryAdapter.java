package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.CompanyEntity;
import com.colegio.backend.infrastructure.persistence.mapper.CompanyMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRepositoryAdapter implements CompanyRepositoryPort {

    private final JpaCompanyRepository repository;
    private final CompanyMapper mapper;


    @Override
    public List<Company> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Company> findByName(String name) {
        return repository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public Optional<Company> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Company save(Company company) {
        CompanyEntity entity = mapper.toEntity(company);
        CompanyEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public String findLastCode() {
        return repository.findLastCode();
    }
}
