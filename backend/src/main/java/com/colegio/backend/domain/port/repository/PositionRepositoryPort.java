package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PositionRepositoryPort {

    List<Position> findAll();

    Optional<Position> findByName(String name);

    Optional<Position> findById(String id);

    Position save(Position position);

    String findLastCode();

    Page<Position> search(String search, Pageable pageable);
}
