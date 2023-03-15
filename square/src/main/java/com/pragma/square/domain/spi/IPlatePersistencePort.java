package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import org.springframework.data.domain.Page;

public interface IPlatePersistencePort {
    PlateModel savePlate(PlateModel plateModel, Long idRestaurant,Long categoryId);
    RestaurantModel getRestaurant (Long id);
    PlateModel getPlate(Long id);

    PlateModel updatePlate(PlateModel plate);

    Page<PlateModel> getPlatesByPage(Long categoryId, Long restaurantId, int page, int size, String property,String sort);

    String findCurrentUserId();
}
