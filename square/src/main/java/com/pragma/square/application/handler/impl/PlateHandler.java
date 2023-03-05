package com.pragma.square.application.handler.impl;

import com.pragma.square.application.handler.IPlateHandler;
import com.pragma.square.application.mapper.IPlateRequestMapper;
import com.pragma.square.application.mapper.IPlateResponseMapper;
import com.pragma.square.application.request.PlateRequestDto;
import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.application.response.PlateResponseDto;
import com.pragma.square.domain.api.IPlateServicePort;
import com.pragma.square.domain.models.PlateModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PlateHandler implements IPlateHandler {
    private final IPlateRequestMapper plateRequestMapper;
    private final IPlateResponseMapper plateResponseMapper;
    private final IPlateServicePort plateServicePort;
    @Override
    public PlateResponseDto savePlate(PlateRequestDto plate, Long idRestaurant,Long categoryId) {
        PlateModel plateModel = plateRequestMapper.toPlateModel(plate);
        return plateResponseMapper.toPlateResponseDto(plateServicePort.savePlate(plateModel,idRestaurant, categoryId));
    }

    @Override
    public PlateResponseDto updatePlate(PlateUpdateRequestDto plateUpdate,Long plateId) {

        return plateResponseMapper.toPlateResponseDto(plateServicePort.UpdatePlate(plateUpdate, plateId));
    }

    @Override
    public PlateResponseDto deactivatePlate(Long plateId) {
        return plateResponseMapper.toPlateResponseDto(plateServicePort.deactivatePlate(plateId));
    }

    @Override
    public Page<PlateResponseDto> getPlateResponseDtoByPage(Long categoryId, Long restaurantId, int page, int size, String property, String sort) {
        return plateServicePort.getPlateResponseDtoByPage(categoryId, restaurantId, page, size, property,sort).map(plateResponseMapper::toPlateResponseDto);
    }


}
