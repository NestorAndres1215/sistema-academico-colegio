package com.colegio.backend.modules.teacher.domain.port.usecase;


import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherUseCase {

    Teacher create(TeacherRequest teacherRequest, MultipartFile foto , MultipartFile cv);

}
