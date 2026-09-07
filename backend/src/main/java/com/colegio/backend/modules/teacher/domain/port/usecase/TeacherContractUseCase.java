package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.application.dto.TeacherContractResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.user_history.application.dto.UserHistoryResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TeacherContractUseCase {

    TeacherContract create(TeacherContract teacherContract, Teacher teacher);

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


}
