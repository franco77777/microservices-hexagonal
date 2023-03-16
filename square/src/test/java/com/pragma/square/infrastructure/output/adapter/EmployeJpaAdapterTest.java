package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.EmployeeModel;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import com.pragma.square.infrastructure.output.mapper.IEmployeeEntityMapper;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeJpaAdapterTest {
@Mock
   IRestaurantRepository restaurantRepository;
    @Mock
    IEmployeeRepository employeeRepository;
    @Mock
   IEmployeeEntityMapper employeeEntityMapper;
    @InjectMocks
    EmployeJpaAdapter employeeJpaAdapter;

    @Test
    void createEmployeeShouldSaveEmployee() {
        //given
        EmployeeEntity employee = new EmployeeEntity();
        EmployeeModel employeeModel = new EmployeeModel();

        //when
        when(employeeEntityMapper.toEntity(any(EmployeeModel.class)))
                .thenReturn(employee);
        employeeJpaAdapter.createEmployee(employeeModel);

        //then
        Mockito.verify(employeeRepository, Mockito.times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    void restaurantBelongUserValidationShouldReturnBoolean() {
        //given

        //when
        when(restaurantRepository.existsByIdAndUserId(anyLong(),anyLong()))
                .thenReturn(true);
        Boolean result = employeeJpaAdapter.restaurantBelongUserValidation(1L,1L);

        //then
        Assertions.assertThat(result).isTrue();
    }
}