package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.IRestaurantServicePort;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        return restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public List<RestaurantModel> getRestaurantsByUserId(Long userId) {
        return restaurantPersistencePort.getRestaurantsByUserId(userId);
    }
}
