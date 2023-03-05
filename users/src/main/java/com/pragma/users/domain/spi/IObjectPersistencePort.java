package com.pragma.users.domain.spi;


import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;
import java.util.Optional;

public interface    IObjectPersistencePort {
    UserModel saveObject(UserModel userModel, AuthorityName role);
    List<UserModel> getAllObjects();
    boolean emailExists(String email);

    UserModel userExists(String email);

    void deleteUser(Long id);
}
