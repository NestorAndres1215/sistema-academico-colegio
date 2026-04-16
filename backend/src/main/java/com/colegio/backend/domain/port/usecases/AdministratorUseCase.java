package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdministratorUseCase {

    Administrator findById(String id);

    List<Administrator> findAll();

    Administrator save(MultipartFile file, AdministratorRequest administratorRequest);

    Administrator update(String id, AdministratorRequest administratorRequest);

    Page<Administrator> getByStatus(boolean status, String search, Pageable pageable);

    Administrator deactivate(String id);

    Administrator activate(String id);
}
