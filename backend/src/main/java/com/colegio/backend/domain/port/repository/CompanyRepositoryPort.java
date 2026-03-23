package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Company;
import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryPort {

    List<Company> findAll();

    Optional<Company> findByName(String name);

    Optional<Company> findById(String id);

    Company save(Company company);

    String findLastCode();
}
