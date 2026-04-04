package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdministratorUseCase {

    List<Administrator> findByGender(String gender);

    List<Administrator> findByFirstName(String firstName);

    List<Administrator> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<Administrator> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Administrator findByDni(String dni);

    List<Administrator> findByPhone(String phone);

    Administrator findById(String id);

    List<Administrator> findAll();

    Administrator save(AdministratorRequest administratorRequest);

    Administrator update(String id, AdministratorRequest administratorRequest);

    Page<Administrator> getByStatus(boolean status, String search, Pageable pageable);
}
