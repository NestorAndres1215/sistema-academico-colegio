package com.colegio.backend.application.dto.position;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PositionRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 300, message = "The description must be between 5 and 300 characters")
    private String description;
}
