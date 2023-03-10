package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.RestaurantModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant (RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants ();
    List<RestaurantModel> getRestaurantsByUserId (Long userId);

    Page<RestaurantModel> getRestaurantsByPage(int page, int size, String sort);

    String getRoleUser(Long userId);
}
