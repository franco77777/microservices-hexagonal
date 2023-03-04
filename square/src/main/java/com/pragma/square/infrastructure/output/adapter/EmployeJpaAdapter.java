package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.spi.IEmployeePersistencePort;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeJpaAdapter implements IEmployeePersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IEmployeeRepository employeeRepository;
    @Override
    public void createEmployee(Long restaurantId, Long employeeId) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .employeeId(employeeId)
                .restaurantId(restaurantId)
                .build();
        employeeRepository.save(employeeEntity);
    }

    @Override
    public Boolean restaurantBelongUserValidation(Long ownerId, Long restaurantId) {
        return restaurantRepository.existsByIdAndUserId(restaurantId,ownerId);
    }
}
