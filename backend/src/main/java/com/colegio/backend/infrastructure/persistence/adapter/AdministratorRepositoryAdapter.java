package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.port.repository.AdministratorRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import com.colegio.backend.infrastructure.persistence.mapper.AdministratorMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaAdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdministratorRepositoryAdapter implements AdministratorRepositoryPort {

    private final JpaAdministratorRepository repository;
    private final AdministratorMapper mapper;

    @Override
    public List<Administrator> findByGender(String gender) {
        return repository.findByGender(gender).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrator> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrator> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName) {
        return repository.findByFirstNameAndPaternalLastName(firstName,paternalLastName).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Administrator> findByFirstNameAndPaternalLastNameAndMaternalLastName(String firstName, String paternalLastName, String maternalLastName) {
        return repository.findByFirstNameAndPaternalLastNameAndMaternalLastName(firstName,paternalLastName,maternalLastName).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Administrator> findByDni(String dni) {
        return repository.findByDni(dni).map(mapper::toDomain);
    }

    @Override
    public List<Administrator> findByPhone(String phone) {
        return repository.findByPhone(phone).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Administrator> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Administrator> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Administrator save(Administrator administrator) {
        AdministratorEntity entity = mapper.toEntity(administrator);
        AdministratorEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public String findLastCode() {
        return repository.findLastCode();
    }

    @Override
    public boolean existsByDni(String dni) {
        return repository.existsByDni(dni);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    @Override
    public Page<Administrator> getByStatus(boolean status, String search, Pageable pageable){
        return repository.searchByStatus(status, search, pageable)
                .map(mapper::toDomain);
    }


}
