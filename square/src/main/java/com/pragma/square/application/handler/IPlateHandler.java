package com.pragma.square.application.handler;

import com.pragma.square.application.request.PlateRequestDto;
import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.application.response.PlateResponseDto;
import org.springframework.data.domain.Page;

public interface IPlateHandler {
    PlateResponseDto savePlate(PlateRequestDto plate, Long idRestaurant,Long categoryId);
    PlateResponseDto updatePlate(PlateUpdateRequestDto plateUpdate, Long plateId);
    PlateResponseDto deactivatePlate(Long plateId);
    Page<PlateResponseDto> getPlateResponseDtoByPage(Long categoryId, Long restaurantId, int page, int size, String property, String sort);
}
