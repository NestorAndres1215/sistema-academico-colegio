package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.teacher.TeacherRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherUseCase {

    Teacher findById(String id);

    List<Teacher> findAll();

    Teacher save(MultipartFile file, TeacherRequest teacherRequest);

    Teacher update(String id, MultipartFile file, TeacherRequest teacherRequest);

    Page<Teacher> getByStatus(Status status, String search, Pageable pageable);

    Teacher deactivate(String id);

    Teacher activate(String id);

}
