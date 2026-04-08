package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {

    List<Teacher> findByGender(String gender);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<Teacher> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Optional<Teacher> findByDni(String dni);

    List<Teacher> findByPhone(String phone);

    Optional<Teacher> findById(String id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    Page<Teacher> getByStatus(boolean status, String search, Pageable pageable);
}
