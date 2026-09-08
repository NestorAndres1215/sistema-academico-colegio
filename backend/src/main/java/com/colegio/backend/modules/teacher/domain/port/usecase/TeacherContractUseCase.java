package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.application.dto.teacher_contract.CreateTeacherContractRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher_contract.TeacherContractResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface TeacherContractUseCase {

    TeacherContract create(TeacherContract teacherContract, Teacher teacher);
    TeacherContract createContract(CreateTeacherContractRequest createTeacherContractRequest,String id);


    TeacherContract activate(Long id);

    TeacherContract deactivate(Long id);

    Page<TeacherContractResponse> findWithFilters(
            String teacherCode,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size,
            String sort
    );

    TeacherContractResponse findById(Long id);

}
