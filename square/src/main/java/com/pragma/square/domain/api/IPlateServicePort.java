package com.pragma.square.domain.api;

import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.domain.models.PlateModel;
import org.springframework.data.domain.Page;

public interface IPlateServicePort {
    PlateModel savePlate(PlateModel plateModel, Long idRestaurant,Long categoryId);

    PlateModel UpdatePlate(PlateUpdateRequestDto plateUpdate,Long plateId);

    PlateModel deactivatePlate(Long plateId);



    Page<PlateModel> getPlateResponseDtoByPage(Long categoryId, Long restaurantId, int page, int size, String property,String sort);
}
