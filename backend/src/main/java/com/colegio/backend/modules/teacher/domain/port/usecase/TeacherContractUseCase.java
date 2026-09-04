package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;

public interface TeacherContractUseCase {

    TeacherContract create(TeacherContract teacherContract, Teacher teacher);
}
