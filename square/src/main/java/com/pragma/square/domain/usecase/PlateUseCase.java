package com.pragma.square.domain.usecase;

import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.domain.api.IPlateServicePort;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IPlatePersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;


    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }

    @Override
    public PlateModel savePlate(PlateModel plateModel, Long restaurantId,Long categoryId) {
        //validating whether the current user corresponds to the restaurant's userId//
        RestaurantModel restaurant = platePersistencePort.getRestaurant(restaurantId);
        Long restaurantUserId = restaurant.getUserId();
        if(validateUserId(restaurantUserId)) throw new DomainException("You are not allowed to save this plate", HttpStatus.UNAUTHORIZED);
        plateModel.setActive(true);
        //saving the plate//
        return platePersistencePort.savePlate(plateModel,restaurantId,categoryId);
    }

    @Override
    public PlateModel UpdatePlate(PlateUpdateRequestDto plateUpdate,Long plateId) {
        //Validating whether the user ID of the associated restaurant of the plate correspond to the current user ID//
        PlateModel plate = platePersistencePort.getPlate(plateId);
        Long plateUserId = plate.getIdRestaurant().getUserId();
        if(validateUserId(plateUserId)) throw new DomainException("You are not allowed to update this plate", HttpStatus.UNAUTHORIZED);
        plate.setPrice(plateUpdate.getPrice());
        plate.setDescription(plateUpdate.getDescription());
        //updating the plate//
        return  platePersistencePort.updatePlate(plate);
    }

    @Override
    public PlateModel deactivatePlate(Long plateId) {
        PlateModel plate = platePersistencePort.getPlate(plateId);
        Long plateUserId = plate.getIdRestaurant().getUserId();
        if(validateUserId(plateUserId)) throw new DomainException("You are not allowed to deactivate this plate", HttpStatus.UNAUTHORIZED);
        plate.setActive(false);
        return platePersistencePort.updatePlate(plate);
    }

    @Override
    public Page<PlateModel> getPlateResponseDtoByPage(Long categoryId, Long restaurantId, int page, int size, String property,String sort) {
        if(!property.matches("price|name")) throw new DomainException("Invalid property, must be price o name", HttpStatus.BAD_REQUEST);
        if(!sort.matches("ascending|descending")) throw new DomainException("Invalid sort, must be ascending or descending", HttpStatus.BAD_REQUEST);
        return platePersistencePort.getPlatesByPage(categoryId,restaurantId,page,size,property,sort);
    }


    public boolean validateUserId(Long userId){
        String currentUserId = platePersistencePort.findCurrentUserId();
        return !Objects.equals(Long.toString(userId), currentUserId);
    }
}
