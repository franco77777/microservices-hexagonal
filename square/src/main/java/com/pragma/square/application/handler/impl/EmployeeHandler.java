package com.pragma.square.application.handler.impl;

import com.pragma.square.application.handler.IEmployeeHandler;
import com.pragma.square.domain.api.IEmployeeServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeHandler implements IEmployeeHandler {
    private final IEmployeeServicePort employeeServicePort;
    @Override
    public void createEmployee(Long ownerId, Long restaurantId, Long employeeId) {
        employeeServicePort.createEmployee(ownerId, restaurantId, employeeId);
    }
}
