package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.EmployeeModel;

public interface IEmployeePersistencePort {
    void createEmployee(EmployeeModel employee);

    Boolean restaurantBelongUserValidation(Long ownerId, Long restaurantId);
}
