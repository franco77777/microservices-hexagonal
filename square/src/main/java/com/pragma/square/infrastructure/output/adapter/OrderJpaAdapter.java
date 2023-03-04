package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.OrderEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor

public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public OrderModel create(OrderModel order) {
        OrderEntity result = orderEntityMapper.toEntity(order);
        OrderEntity orderEntity = orderRepository.save(result);
        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public PlateModel findPlateById(Long plateId) {
        PlateEntity plateEntity = plateRepository.findById(plateId).orElseThrow(()->new InfrastructureException("Plate id: "+ plateId + " not found", HttpStatus.BAD_REQUEST));
        return plateEntityMapper.toPlateModel(plateEntity);
    }

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id).orElseThrow(()->new InfrastructureException("restaurant id: "+ id + " not found", HttpStatus.BAD_REQUEST));
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public Boolean orderExists(Long idClient) {
        return orderRepository.existsByIdClient(idClient);
    }
}
