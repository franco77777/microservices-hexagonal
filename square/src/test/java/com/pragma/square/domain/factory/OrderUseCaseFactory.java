package com.pragma.square.domain.factory;

import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.RestaurantModel;


public class OrderUseCaseFactory {
    public static OrderModel getRestaurantModel() {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);
        OrderModel expected = new OrderModel();
        expected.setIdRestaurant(restaurantModel);
        expected.setStatus("preparing");
        return expected;
    }

}
