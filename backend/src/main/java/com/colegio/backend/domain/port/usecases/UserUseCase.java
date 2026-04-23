package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.application.dto.auth.PasswordRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.User;
import java.util.List;


public interface UserUseCase {

    User findByEmail(String email);

    List<User> findByStatus(Status status);

    List<User> findByEmailAndStatus(String email, Status status);

    User save(String id , String email, String password, String role);

    User update(String id , String email, String password,String role);

    User activateUser (String id);

    User deactivateUser (String id);
    User changePassword(String userId, PasswordRequest request);
}
