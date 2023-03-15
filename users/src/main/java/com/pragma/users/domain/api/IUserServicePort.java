package com.pragma.users.domain.api;

import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {
    TokenModel saveUser(UserModel userModel, AuthorityNameModel role);
    List<UserModel> getAllUsers();

    boolean emailExists(String email);

    TokenModel userLogin(String email, String password);

    void deleteUser(Long id);

    UserModel getUser(Long userId);

    TokenModel validateToken(String token);

    UserModel saveOwner(UserModel userModel, AuthorityNameModel authorityNameModel);
}

