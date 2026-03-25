package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaAdministratorRepository  extends JpaRepository<AdministratorEntity,String> {

    List<AdministratorEntity> findByGender(String gender);

    List<AdministratorEntity> findByFirstName(String firstName);

    List<AdministratorEntity> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<AdministratorEntity> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Optional<AdministratorEntity> findByDni(String dni);

    List<AdministratorEntity> findByPhone(String phone);

    @Query("SELECT MAX(c.id) FROM AdministratorEntity c")
    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);
}
