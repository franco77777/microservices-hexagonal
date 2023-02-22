package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant (RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants ();
    List<RestaurantModel> getRestaurantsByUserId (Long userId);
}
