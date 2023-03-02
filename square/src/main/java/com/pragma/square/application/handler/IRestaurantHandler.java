package com.pragma.square.application.handler;

import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.RestaurantPageDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantHandler {
    RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);
    List<RestaurantResponseDto> getAllRestaurants();
    List<RestaurantResponseDto> getRestaurantsByUserId(Long userId);
    Page<RestaurantPageDto> getRestaurantsByPage(int page, int size, String sort);
}
