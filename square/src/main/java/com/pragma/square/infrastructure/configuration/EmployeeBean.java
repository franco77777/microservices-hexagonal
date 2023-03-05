package com.pragma.square.infrastructure.configuration;

import com.pragma.square.domain.api.IEmployeeServicePort;
import com.pragma.square.domain.spi.IEmployeePersistencePort;
import com.pragma.square.domain.usecase.EmployeeUseCase;
import com.pragma.square.infrastructure.output.adapter.EmployeJpaAdapter;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmployeeBean {
    private final IRestaurantRepository restaurantRepository;
    private final IEmployeeRepository employeeRepository;
    @Bean
    public IEmployeePersistencePort employeePersistencePort() {
        return new EmployeJpaAdapter(restaurantRepository, employeeRepository);
    }
    @Bean
    public IEmployeeServicePort employeeServicePort() {
        return new EmployeeUseCase(employeePersistencePort());
    }
}
