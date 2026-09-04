package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;

public interface TeacherContractUseCase {

    TeacherContract create(TeacherContract teacherContract, Teacher teacher);

    TeacherContract activate(Long id);

    TeacherContract deactivate(Long id);

}
