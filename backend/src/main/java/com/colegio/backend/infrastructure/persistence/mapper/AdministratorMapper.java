package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministratorMapper {

    private final  UserMapper userMapper;

    public Administrator toDomain(AdministratorEntity entity) {
        if (entity == null) return null;


        return Administrator.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .paternalLastName(entity.getPaternalLastName())
                .maternalLastName(entity.getMaternalLastName())
                .age(entity.getAge())
                .dni(entity.getDni())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .profile(entity.getProfile())
                .gender(entity.getGender())
                .nationality(entity.getNationality())
                .status(entity.getStatus())
                .user(userMapper.toDomain(entity.getUserEntity()))
                .build();
    }

    public AdministratorEntity toEntity(Administrator dto) {
        if (dto == null) return null;

        return AdministratorEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .paternalLastName(dto.getPaternalLastName())
                .maternalLastName(dto.getMaternalLastName())
                .age(dto.getAge())
                .dni(dto.getDni())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .profile(dto.getProfile())
                .gender(dto.getGender())
                .nationality(dto.getNationality())
                .status(dto.getStatus())
                .userEntity(userMapper.toEntity(dto.getUser()))
                .build();
    }
}
