package com.pragma.users.domain.spi;

import com.pragma.users.domain.model.Square.RestaurantModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public interface    IObjectPersistencePort {
    UserModel saveObject(UserModel userModel, AuthorityName role);
    List<UserModel> getAllObjects();

    UserModel emailExists(String email);

}
