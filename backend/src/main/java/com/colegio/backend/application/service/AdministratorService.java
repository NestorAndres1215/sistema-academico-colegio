package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.exception.ConflictException;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.AdministratorRepositoryPort;
import com.colegio.backend.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.domain.port.usecases.AdministratorUseCase;
import com.colegio.backend.domain.port.usecases.UserUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdministratorService implements AdministratorUseCase {

    private final AdministratorRepositoryPort repositoryPort;
    private final UserUseCase userUseCase;

    @Override
    public List<Administrator> findByGender(String gender) {
        return repositoryPort.findByGender(gender);
    }

    @Override
    public List<Administrator> findByFirstName(String firstName) {
        return repositoryPort.findByFirstName(firstName);
    }

    @Override
    public List<Administrator> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName) {
        return repositoryPort.findByFirstNameAndPaternalLastName(firstName, paternalLastName);
    }

    @Override
    public List<Administrator> findByFirstNameAndPaternalLastNameAndMaternalLastName(String firstName, String paternalLastName, String maternalLastName) {
        return repositoryPort.findByFirstNameAndPaternalLastNameAndMaternalLastName(firstName, paternalLastName, maternalLastName);
    }

    @Override
    public Administrator findByDni(String dni) {
        return repositoryPort.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("DNI NO ENCONTRADO"));
    }

    @Override
    public List<Administrator> findByPhone(String phone) {
        return repositoryPort.findByPhone(phone);
    }

    @Override
    public Administrator findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CODIGO NO ENCONTRADO"));
    }

    @Override
    public List<Administrator> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Administrator save(AdministratorRequest request) {
System.out.println(request);
        if (repositoryPort.existsByDni(request.getDni())) {
            throw new ConflictException("El DNI ya está registrado");
        }

        if (request.getPhone() != null && repositoryPort.existsByPhone(request.getPhone())) {
            throw new ConflictException("El teléfono ya está registrado");
        }

        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());

        User user = userUseCase.save(
                "",
                request.getEmail(),
                request.getPassword(),
                "ROLE_ADMINISTRATOR"
        );

        Administrator administrator = Administrator.builder()
                .id(newCode)
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .paternalLastName(request.getPaternalLastName())
                .maternalLastName(request.getMaternalLastName())
                .dni(request.getDni())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .profile(request.getProfile())
                .gender(request.getGender())
                .nationality(request.getNationality())
                .user(user)
                .build();

        return repositoryPort.save(administrator);
    }


    @Override
    public Administrator update(String id, AdministratorRequest request) {

        Administrator existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado"));

        if (!existing.getDni().equals(request.getDni()) &&
                repositoryPort.existsByDni(request.getDni())) {
            throw new ConflictException("El DNI ya está registrado");
        }

        if (request.getPhone() != null &&
                !request.getPhone().equals(existing.getPhone()) &&
                repositoryPort.existsByPhone(request.getPhone())) {
            throw new ConflictException("El teléfono ya está registrado");
        }

        existing.setFirstName(request.getFirstName());
        existing.setMiddleName(request.getMiddleName());
        existing.setPaternalLastName(request.getPaternalLastName());
        existing.setMaternalLastName(request.getMaternalLastName());
        existing.setDni(request.getDni());
        existing.setPhone(request.getPhone());
        existing.setBirthDate(request.getBirthDate());
        existing.setProfile(request.getProfile());
        existing.setGender(request.getGender());
        existing.setNationality(request.getNationality());

        return repositoryPort.save(existing);
    }


}
