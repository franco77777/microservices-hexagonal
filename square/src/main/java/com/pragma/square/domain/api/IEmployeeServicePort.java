package com.pragma.square.domain.api;

public interface IEmployeeServicePort {
    void createEmployee(Long ownerId, Long restaurantId, Long employeeId);
}
