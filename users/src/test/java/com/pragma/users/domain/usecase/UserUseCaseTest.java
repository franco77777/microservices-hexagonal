package com.pragma.users.domain.usecase;

import com.pragma.users.domain.model.AuthorityNameModel;
import com.pragma.users.domain.model.TokenModel;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;



@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {


    @Mock
    private IUserPersistencePort userPersistencePort;
    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {

    }

/////////////////////////////////// <--- SAVE USER ---> /////////////////////////////////////////////
    @Test
    void saveUserShouldReturnUserModel() {
        //given
        UserModel user = new UserModel();
        user.setEmail("kenaa@example.com");
        TokenModel token = new TokenModel();
        token.setToken("123456");

        //when
        Mockito.when(userPersistencePort.saveUser(user))
                .thenReturn(token);
         TokenModel result = userUseCase.saveUser(user,AuthorityNameModel.ROLE_EMPLOYEE);

        //then
        Assertions.assertEquals(token,result);
    }

    /////////////////////////////////// <--- GET ALL USERS ---> /////////////////////////////////////////////
    @Test

    void getAllUsersShouldReturnListOfUserModel() {
        //given
        List<UserModel> esperado = new ArrayList<>();

        //when
        Mockito.when(userPersistencePort.getAllUsers())
                .thenReturn(esperado);
        final List<UserModel> resultado = userUseCase.getAllUsers();

        //then
        Assertions.assertEquals(esperado,resultado);
        Mockito.verify(userPersistencePort,Mockito.times(1)).getAllUsers();
    }
    /////////////////////////////////// <--- EMAIL EXISTS ---> /////////////////////////////////////////////
    @Test
    void emailExistsShouldReturnBoolean() {
        //given
        Boolean esperado = true;

        //when
        Mockito.when(userPersistencePort.emailExists("kenaa@example.com"))
              .thenReturn(esperado);
        final Boolean resultado = userUseCase.emailExists("kenaa@example.com");

        //then
        Assertions.assertEquals(esperado,resultado);
    }

    /////////////////////////////////// <--- USER EXISTS ---> /////////////////////////////////////////////

    @Test
    void userLoginShouldReturnTokenModel() {
        //given
        String email = "kenaa@example.com";
        String password = "password";
       TokenModel expected = new TokenModel();
       expected.setToken("token");

        //when
        Mockito.when(userPersistencePort.userLogin(email,password))
                .thenReturn(expected);
        TokenModel result = userUseCase.userLogin("kenaa@example.com","password");

        //then
        Assertions.assertEquals(expected,result);

    }

    @Test
    void deleteUser() {
        //given
        Long userId = 1L;

        //when
        userUseCase.deleteUser(userId);

        //then
        Mockito.verify(userPersistencePort,Mockito.times(1)).deleteUser(1L);
    }
}