package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.model.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepositoryPort {

    List<Position> findAll();

    Optional<Position> findByName(String name);

    Optional<Position> findById(String id);

    Position save(Position position);

    String findLastCode();
}
