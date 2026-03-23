package com.colegio.backend.application.dto.position;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PositionRequest {

    private String name;
    private String description;
}
