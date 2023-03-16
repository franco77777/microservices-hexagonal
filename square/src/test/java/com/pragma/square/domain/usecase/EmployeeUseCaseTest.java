package com.pragma.square.domain.usecase;

import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.EmployeeModel;
import com.pragma.square.domain.spi.IEmployeePersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class EmployeeUseCaseTest {

    @Mock
    IEmployeePersistencePort employeePersistencePort;
    @InjectMocks
    EmployeeUseCase employeeUseCase;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldCreateEmployee() {
        //given
        Long ownerId = 1L;
        Long restaurantId=1L;
        Long employeeId=1L;

        //when
        Mockito.when(employeePersistencePort.restaurantBelongUserValidation(ownerId, restaurantId))
                .thenReturn(true);
        employeeUseCase.createEmployee(ownerId, restaurantId, employeeId);

        //then
        Mockito.verify(employeePersistencePort, Mockito.times(1)).restaurantBelongUserValidation(ownerId,restaurantId);
        Mockito.verify(employeePersistencePort, Mockito.times(1)).createEmployee(any(EmployeeModel.class));
    }

    @Test
    void ShouldThrowExceptionWhenCreateEmployeeFails() {
        //given
        Long ownerId = 1L;
        Long restaurantId=1L;
        Long employeeId=1L;

        //when
        Mockito.when(employeePersistencePort.restaurantBelongUserValidation(ownerId, restaurantId))
                .thenReturn(false);

        //then
        Assertions.assertThrows(DomainException.class, () -> {
            employeeUseCase.createEmployee(ownerId, restaurantId, employeeId);
        });

    }
}