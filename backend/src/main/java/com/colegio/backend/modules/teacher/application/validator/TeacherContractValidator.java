package com.colegio.backend.modules.teacher.application.validator;

import com.colegio.backend.shared.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TeacherContractValidator {

    private static final Set<String> CONTRACT_TYPES = Set.of(
            "Tiempo completo",
            "Tiempo parcial",
            "Por horas",
            "Temporal"
    );

    public void validateContract(
            String contractType,
            LocalDate startDate,
            LocalDate endDate
    ) {

        validateContractType(contractType);
        validateContractDates(startDate, endDate);
    }

    private void validateContractType(String contractType) {

        if (!CONTRACT_TYPES.contains(contractType)) {
            throw new BadRequestException(
                    "El tipo de contrato no es válido"
            );
        }
    }

    private void validateContractDates(
            LocalDate startDate,
            LocalDate endDate
    ) {

        if (endDate == null) {
            return;
        }

        if (!endDate.isAfter(startDate)) {
            throw new BadRequestException(
                    "La fecha de finalización debe ser posterior a la fecha de inicio"
            );
        }
    }
}