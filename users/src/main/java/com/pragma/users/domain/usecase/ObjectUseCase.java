package com.pragma.users.domain.usecase;


import com.pragma.users.domain.api.IObjectServicePort;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IObjectPersistencePort;
import com.pragma.users.infrastructure.output.utils.AuthorityName;

import java.util.List;

public class ObjectUseCase  implements IObjectServicePort {
    private final IObjectPersistencePort objectPersistencePort;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort) {
        this.objectPersistencePort = objectPersistencePort;
    }

    @Override
    public UserModel saveObject(UserModel userModel, AuthorityName role) {
       return objectPersistencePort.saveObject(userModel,role);
    }

    @Override
    public List<UserModel> getAllObjects() {
        return objectPersistencePort.getAllObjects();
    }

    @Override
    public UserModel emailExists(String email) {
        return objectPersistencePort.emailExists(email);
    }


}
