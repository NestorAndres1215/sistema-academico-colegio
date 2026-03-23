package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.application.dto.position.PositionRequest;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.model.Position;

import java.util.List;

public interface PositionUseCase {

    List<Position> findAll();

    Position findByName(String name);

    Position findById(String id);

    Position save(PositionRequest positionRequest);

    Position update (String id,PositionRequest positionRequest);
}
