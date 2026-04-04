package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdministratorRepositoryPort {

    List<Administrator> findByGender(String gender);

    List<Administrator> findByFirstName(String firstName);

    List<Administrator> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<Administrator> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Optional<Administrator> findByDni(String dni);

    List<Administrator> findByPhone(String phone);

    Optional<Administrator> findById(String id);

    List<Administrator> findAll();

    Administrator save(Administrator administrator);

    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    Page<Administrator> getByStatus(boolean status, String search, Pageable pageable);
}
