package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.port.repository.AdministratorRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import com.colegio.backend.infrastructure.persistence.mapper.flat.AdministratorMapper;
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
    public Page<Administrator> getByStatus(Status status, String search, Pageable pageable){
        return repository.searchByStatus(status, search, pageable)
                .map(mapper::toDomain);
    }


}
