package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.position.PositionRequest;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Position;
import com.colegio.backend.domain.port.repository.PositionRepositoryPort;
import com.colegio.backend.domain.port.usecases.PositionUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionUseCase {

    private final PositionRepositoryPort repositoryPort;

    @Override
    public List<Position> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Position findByName(String name) {
        return repositoryPort.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("POSITION NO ENCONTRADO"));
    }

    @Override
    public Position findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POSITION NO ENCONTRADO"));
    }

    @Override
    public Position save(PositionRequest positionRequest) {
        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());
        Position position = Position.builder()
                .id(newCode)
                .name(positionRequest.getName())
                .description(positionRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        return repositoryPort.save(position);
    }

    @Override
    public Position update(String id, PositionRequest positionRequest) {

        Position position = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POSITION NO ENCONTRADO"));
        position.setName(positionRequest.getName());
        position.setDescription(positionRequest.getDescription());
        return repositoryPort.save(position);
    }
}
