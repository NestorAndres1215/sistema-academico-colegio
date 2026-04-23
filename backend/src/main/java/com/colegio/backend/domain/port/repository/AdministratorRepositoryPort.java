package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepositoryPort {

    Optional<Administrator> findById(String id);

    List<Administrator> findAll();

    Administrator save(Administrator administrator);

    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    Page<Administrator> getByStatus(Status status, String search, Pageable pageable);
}
