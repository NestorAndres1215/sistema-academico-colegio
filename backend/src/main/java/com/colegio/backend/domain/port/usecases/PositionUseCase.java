package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.position.PositionRequest;
import com.colegio.backend.domain.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PositionUseCase {

    List<Position> findAll();

    Position findByName(String name);

    Position findById(String id);

    Position save(PositionRequest positionRequest);

    Position update (String id,PositionRequest positionRequest);

    Page<Position> search(String search, Pageable pageable);
}
