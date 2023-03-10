package com.pragma.users.domain.api;

import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface IUserServicePort {
    UserModel saveUser(UserModel userModel, AuthorityName role);
    List<UserModel> getAllUsers();

    boolean emailExists(String email);

    UserModel userExists(String email, String password);

    void deleteUser(Long id);
}

