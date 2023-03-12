package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.PlateQuantityModel;
import com.pragma.square.domain.models.RestaurantModel;
import org.springframework.data.domain.Page;

public interface IOrderPersistencePort {
    OrderModel create(OrderModel order);

    PlateModel findPlateById(Long plateId);

    RestaurantModel findRestaurantById(Long id);

    Boolean orderExists();

    Long findEmployee(Long employeeId);

    Page<OrderModel> findByStatus(Long restaurantId, int page, int size, String sort, String status,String property);

    OrderModel findOrderById(Long plateId);

    OrderModel updateOrder(OrderModel order);

    String findClientPhone(Long orderClientId);


    void deleteOrder(Long orderId);

    PlateQuantityModel createPlateQuantity(PlateQuantityModel quantity);
}
