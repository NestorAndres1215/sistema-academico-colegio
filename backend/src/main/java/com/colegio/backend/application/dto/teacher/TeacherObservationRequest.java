package com.colegio.backend.application.dto.teacher;

import com.colegio.backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherObservationRequest {

    private String teacher;
    private String admin;
    private  String observation;
    private Status status;
}
