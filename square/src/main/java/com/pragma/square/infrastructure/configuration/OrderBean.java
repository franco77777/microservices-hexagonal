package com.pragma.square.infrastructure.configuration;

import com.pragma.square.domain.api.IOrderServicePort;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.domain.usecase.OrderUseCase;
import com.pragma.square.infrastructure.output.adapter.OrderJpaAdapter;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateQuantityEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.*;
import com.pragma.square.infrastructure.utils.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor

public class OrderBean {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IEmployeeRepository employeeRepository;
    private final IPlateQuantityEntityMapper plateQuantityEntityMapper;
    private final IPlateQuantityRepository plateQuantityRepository;
    private final UserService userService;
    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(plateRepository,plateEntityMapper,restaurantRepository,restaurantEntityMapper,orderRepository,orderEntityMapper,employeeRepository,plateQuantityEntityMapper,plateQuantityRepository,userService);
    }
    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(orderPersistencePort());
    }
}
