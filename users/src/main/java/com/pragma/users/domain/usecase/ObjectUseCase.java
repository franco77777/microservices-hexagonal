package com.pragma.users.domain.usecase;
import com.pragma.users.domain.api.IObjectServicePort;
import com.pragma.users.domain.exception.DomainException;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IObjectPersistencePort;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public class ObjectUseCase  implements IObjectServicePort {
    private final IObjectPersistencePort objectPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort, PasswordEncoder passwordEncoder) {
        this.objectPersistencePort = objectPersistencePort;
        this.passwordEncoder = passwordEncoder;
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
    public boolean emailExists(String email) {
        return objectPersistencePort.emailExists(email);
    }

    @Override
    public UserModel userExists(String email, String password) {
        UserModel user = objectPersistencePort.userExists(email);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new DomainException("Wrong password", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        objectPersistencePort.deleteUser(id);
    }


}
