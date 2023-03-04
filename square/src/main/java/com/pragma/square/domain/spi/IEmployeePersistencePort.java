package com.pragma.square.domain.spi;

public interface IEmployeePersistencePort {
    void createEmployee(Long restaurantId, Long employeeId);

    Boolean restaurantBelongUserValidation(Long ownerId, Long restaurantId);
}
