package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import com.pragma.square.infrastructure.output.entity.OrderEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor

public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IEmployeeRepository employeeRepository;

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

    @Override
    public Long findEmployee(Long parseLong) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(parseLong).orElseThrow(()->new InfrastructureException("you are not an employee of this restaurant",HttpStatus.UNAUTHORIZED));
        return employeeEntity.getRestaurantId();
    }

    @Override
    public Page<OrderModel> findByStatus(Long restaurantId, int page, int size, String sort, String status,String property) {
        Page<OrderEntity> result;
        if(sort.equals("ascending")){
            result = orderRepository.findAllByIdRestaurant_IdAndStatus( restaurantId,status, PageRequest.of(page, size)
                    .withSort(Sort.by(Sort.Direction.ASC,property))).orElseThrow(()-> new InfrastructureException("no orders found", HttpStatus.BAD_REQUEST));
        } else {
            result = orderRepository.findAllByIdRestaurant_IdAndStatus( restaurantId,status, PageRequest.of(page, size)
                    .withSort(Sort.by(Sort.Direction.DESC,property))).orElseThrow(()-> new InfrastructureException("no orders found", HttpStatus.BAD_REQUEST));
        }
        return result.map(orderEntityMapper::toModel);
    }

    @Override
    public OrderModel findOrderById(Long plateId) {
        OrderEntity orderEntity = orderRepository.findById(plateId).orElseThrow(()->new InfrastructureException("plate id: "+ plateId + " not found", HttpStatus.BAD_REQUEST));
        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public OrderModel updateToPreparing(OrderModel order) {
        OrderEntity resultUpdate = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toModel(resultUpdate);
    }
}
