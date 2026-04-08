package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.Position;
import com.colegio.backend.domain.port.repository.PositionRepositoryPort;
import com.colegio.backend.infrastructure.persistence.entity.CompanyEntity;
import com.colegio.backend.infrastructure.persistence.entity.PositionsEntity;
import com.colegio.backend.infrastructure.persistence.mapper.PositionMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PositionRepositoryAdapter implements PositionRepositoryPort {

    private final JpaPositionRepository repository;
    private final PositionMapper mapper;

    @Override
    public List<Position> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Position> findByName(String name) {
        return repository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public Optional<Position> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Position save(Position position) {
        PositionsEntity entity = mapper.toEntity(position);
        PositionsEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public String findLastCode() {
        return repository.findLastCode();
    }

    @Override
    public Page<Position> search(String search, Pageable pageable) {
        return repository.search(search, pageable).map(mapper::toDomain);
    }
}
