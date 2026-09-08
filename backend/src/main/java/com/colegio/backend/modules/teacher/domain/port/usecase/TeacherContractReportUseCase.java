package com.colegio.backend.modules.teacher.domain.port.usecase;

public interface TeacherContractReportUseCase {

    byte[] generateContractPdf(Long contractId);
}
