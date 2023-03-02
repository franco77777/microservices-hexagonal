package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.IRestaurantServicePort;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

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

    @Override
    public Page<RestaurantModel> getRestaurantsByPage(int page, int size, String sort) {
        if(!sort.matches("ascending|descending")) throw new DomainException("invalid sort, must be ascending or descending", HttpStatus.BAD_REQUEST);
        return restaurantPersistencePort.getRestaurantsByPage(page, size, sort);
    }
}
