package com.pragma.users.domain.usecase;

import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import java.util.List;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;

    }

    @Override
    public TokenModel saveUser(UserModel userModel, AuthorityNameModel role) {
        userModel.setRoles(role);
        return userPersistencePort.saveUser(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public boolean emailExists(String email) {
        return userPersistencePort.emailExists(email);
    }

    @Override
    public TokenModel userLogin(String email, String password) {
        return userPersistencePort.userLogin(email,password);
    }

    @Override
    public void deleteUser(Long id) {
        userPersistencePort.deleteUser(id);
    }

    @Override
    public UserModel getUser(Long userId) {
        return userPersistencePort.getUser(userId);
    }

    @Override
    public TokenModel validateToken(String token) {
        return userPersistencePort.validateToken(token);
    }

    @Override
    public UserModel saveOwner(UserModel userModel, AuthorityNameModel authorityNameModel) {
        userModel.setRoles(authorityNameModel);
        return userPersistencePort.saveOwner(userModel);
    }
}
