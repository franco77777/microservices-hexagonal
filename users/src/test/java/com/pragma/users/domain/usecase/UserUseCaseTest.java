package com.pragma.users.domain.usecase;

import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IUserPersistencePort userPersistencePort;
    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {

    }


    @Test
    void saveUser() {
        //given
        UserModel esperado = new UserModel();
        esperado.setId(1L);
        esperado.setEmail("kenaa@example.com");
        esperado.setRoles(AuthorityName.ROLE_EMPLOYEE);
        esperado.setMobile("+5493874692393");
        UserModel parametroUser = new UserModel();
        parametroUser.setEmail("kenaa@example.com");

        //when
        Mockito.when(userPersistencePort.saveUser(parametroUser))
                .thenReturn(esperado);
        final UserModel resultado = userUseCase.saveUser(parametroUser,AuthorityName.ROLE_EMPLOYEE);

        //then
        Assertions.assertEquals(esperado,resultado);
    }

    @Test

    void getAllUsers() {
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

    @Test
    void emailExists() {
        //given
        Boolean esperado = true;

        //when
        Mockito.when(userPersistencePort.emailExists("kenaa@example.com"))
              .thenReturn(esperado);
        final Boolean resultado = userUseCase.emailExists("kenaa@example.com");

        //then
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    void ShouldGivenUser() {

        //given
        Authentication authentication = Mockito.mock(Authentication.class);
        UserModel esperado = new UserModel();
        esperado.setEmail("kenaa@example.com");
        esperado.setPassword(passwordEncoder.encode("password"));

        //when
        Mockito.when(userPersistencePort.userExists("kenaa@example.com"))
                .thenReturn(esperado);
        Mockito.when(passwordEncoder.matches("password",esperado.getPassword()))
                .thenReturn(true);
        UserModel resultado = userUseCase.userExists("kenaa@example.com","password");

        //then
        Assertions.assertEquals(esperado,resultado);

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