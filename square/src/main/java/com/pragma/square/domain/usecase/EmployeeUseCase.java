package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.IEmployeeServicePort;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.EmployeeModel;
import com.pragma.square.domain.spi.IEmployeePersistencePort;
import org.springframework.http.HttpStatus;

public class EmployeeUseCase implements IEmployeeServicePort {
    private final IEmployeePersistencePort employeePersistencePort;

    public EmployeeUseCase(IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public void createEmployee(Long ownerId, Long restaurantId, Long employeeId) {
        Boolean restaurantBelongUserValidation = employeePersistencePort.restaurantBelongUserValidation(ownerId, restaurantId);
        if (!restaurantBelongUserValidation) throw new DomainException("Restaurant does not belong to user", HttpStatus.BAD_REQUEST);
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeId(employeeId);
        employee.setRestaurantId(restaurantId);
        employeePersistencePort.createEmployee(employee);
    }
}
