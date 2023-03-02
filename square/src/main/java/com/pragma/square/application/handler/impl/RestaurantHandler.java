package com.pragma.square.application.handler.impl;

import com.pragma.square.application.handler.IRestaurantHandler;
import com.pragma.square.application.mapper.IRestaurantRequestMapper;
import com.pragma.square.application.mapper.IRestaurantResponseMapper;
import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.RestaurantPageDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import com.pragma.square.domain.api.IRestaurantServicePort;
import com.pragma.square.domain.models.RestaurantModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
private final IRestaurantServicePort restaurantServicePort;
private final IRestaurantRequestMapper restaurantRequestMapper;
private final IRestaurantResponseMapper restaurantResponseMapper;
    @Override
    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurantModel(restaurantRequestDto);
        return restaurantResponseMapper.toRestaurantResponseDto(restaurantServicePort.saveRestaurant(restaurantModel));
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return  restaurantResponseMapper.toRestaurantResponseDtoList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public List<RestaurantResponseDto> getRestaurantsByUserId(Long userId) {
        return restaurantResponseMapper.toRestaurantResponseDtoList(restaurantServicePort.getRestaurantsByUserId(userId));
    }
//    Page<Order> persistedOrderPage = orderQueryRepository.search();
//
//    Page<OrderDTO> orderPage = persistedOrderPage.map(persistedOrder -> {
//        OrderDTO order = mapper.toOrderDTO(persistedOrder);
//        // do another action
//        return order;

    @Override
    public Page<RestaurantPageDto> getRestaurantsByPage(int page, int size, String sort) {
        return restaurantServicePort.getRestaurantsByPage(page, size,sort).map(restaurantResponseMapper::toRestaurantPageDto);
    }}
