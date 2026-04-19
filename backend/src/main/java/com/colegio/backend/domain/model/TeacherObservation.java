package com.colegio.backend.domain.model;

import com.colegio.backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherObservation {

    private String id;
    private Teacher teacher;
    private Administrator admin;
    private String observation;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
