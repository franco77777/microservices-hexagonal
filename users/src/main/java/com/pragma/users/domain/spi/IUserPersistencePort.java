package com.pragma.users.domain.spi;


import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {
    TokenModel saveUser(UserModel userModel);
    List<UserModel> getAllUsers();
    boolean emailExists(String email);

    TokenModel userLogin(String email, String password);

    void deleteUser(Long id);

    UserModel getUser(Long userId);

    TokenModel validateToken(String token);

    UserModel saveOwner(UserModel userModel);
}
