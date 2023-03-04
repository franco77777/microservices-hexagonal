package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface IOrderPersistencePort {
    OrderModel create(OrderModel order);

    PlateModel findPlateById(Long plateId);

    RestaurantModel findRestaurantById(Long id);

    Boolean orderExists(Long idClient);
}
