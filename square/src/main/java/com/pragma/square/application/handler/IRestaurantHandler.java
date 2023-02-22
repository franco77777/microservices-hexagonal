package com.pragma.square.application.handler;

import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {
    RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);
    List<RestaurantResponseDto> getAllRestaurants();
    List<RestaurantResponseDto> getRestaurantsByUserId(Long userId);
}
