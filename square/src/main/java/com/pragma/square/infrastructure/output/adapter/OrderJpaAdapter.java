package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.*;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.*;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateQuantityEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.*;
import com.pragma.square.infrastructure.utils.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;


@RequiredArgsConstructor

public class OrderJpaAdapter implements IOrderPersistencePort {
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
    public Boolean orderExists() {
        String idClient = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return orderRepository.existsByIdClient(Long.parseLong(idClient));
    }

    @Override
    public Long findEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new InfrastructureException("you are not an employee",HttpStatus.UNAUTHORIZED));
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
    public OrderModel findOrderById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(()->new InfrastructureException("order id: "+ orderId + " not found", HttpStatus.BAD_REQUEST));
        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public OrderModel updateOrder(OrderModel order) {
        OrderEntity resultUpdate = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toModel(resultUpdate);
    }

    @Override
    public String findClientPhone(Long orderClientId) {
        return userService.getClientPhone(orderClientId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public PlateQuantityModel createPlateQuantity(PlateQuantityModel quantity) {
        PlateQuantityEntity result = plateQuantityEntityMapper.toEntity(quantity);
        PlateQuantityEntity response = plateQuantityRepository.save(result);
        return plateQuantityEntityMapper.toModel(response);
    }

    @Override
    public OrderModel findOrderByUserId(Long userIdLogged) {
        OrderEntity orderEntity = orderRepository.findByIdClient(userIdLogged).orElseThrow(()->new InfrastructureException("the current user no have orders", HttpStatus.NOT_FOUND));
        return orderEntityMapper.toModel(orderEntity);
    }


}
