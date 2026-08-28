package com.colegio.backend.modules.companies.domain.port.usecase;

import com.colegio.backend.modules.companies.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyUseCase {

    Page<Company> getByStatus(String status, String search, Pageable pageable);

    Optional<Company> findByEmail(String email);

    Optional<Company> findByCode(String code);

    List<Company> findByStatus(String status);

    List<Company> findAll();

    Optional<Company> findById(Long id);

    Company save (Company company);


    Company activate(Long id);

    Company deactivate(Long id);

}
