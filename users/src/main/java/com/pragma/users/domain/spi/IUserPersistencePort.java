package com.pragma.users.domain.spi;


import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);
    List<UserModel> getAllUsers();
    boolean emailExists(String email);

    UserModel userExists(String email);

    void deleteUser(Long id);
}
