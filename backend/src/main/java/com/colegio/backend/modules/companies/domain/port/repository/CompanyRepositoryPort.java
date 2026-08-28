package com.colegio.backend.modules.companies.domain.port.repository;

import com.colegio.backend.modules.companies.domain.model.Company;

import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CompanyRepositoryPort {

    Page<Company> getByStatus(String status, String search, Pageable pageable);

    Optional<Company> findByEmail(String email);

    Optional<Company> findByCode(String code);

    List<Company> findByStatus(String status);

    List<Company> findAll();

    Optional<Company> findById(Long id);

    Company save (Company company);
}
