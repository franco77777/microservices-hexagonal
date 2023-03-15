package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IPlatePersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class PlateJpaAdapter implements IPlatePersistencePort {

private final IPlateRepository plateRepository;
private final IPlateEntityMapper plateEntityMapper;
private final IRestaurantRepository restaurantRepository;
private final IRestaurantEntityMapper restaurantEntityMapper;
private final ICategoryRepository categoryRepository;

    @Override
    public PlateModel savePlate(PlateModel plateModel, Long idRestaurant,Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(()-> new InfrastructureException("Category not found",HttpStatus.NOT_FOUND));
        RestaurantEntity restaurant = restaurantRepository.findById(idRestaurant ).orElseThrow(()-> new InfrastructureException("Restaurant not found",HttpStatus.NOT_FOUND));
        PlateEntity plate = plateEntityMapper.toPlateEntity(plateModel);
        plate.setIdRestaurant(restaurant);
        plate.setIdCategory(category);
        return plateEntityMapper.toPlateModel(plateRepository.save(plate));
    }

    @Override
    public RestaurantModel getRestaurant(Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(()-> new InfrastructureException("Restaurant not found", HttpStatus.NOT_FOUND));
        return restaurantEntityMapper.toRestaurantModel(restaurant);
    }

    @Override
    public PlateModel getPlate(Long id) {
        PlateEntity plate = plateRepository.findById(id).orElseThrow(()-> new InfrastructureException("Plate not found", HttpStatus.NOT_FOUND));
        return plateEntityMapper.toPlateModel(plate);
    }

    @Override
    public PlateModel updatePlate(PlateModel plate) {
        PlateEntity plateEntity = plateEntityMapper.toPlateEntity(plate);
        return plateEntityMapper.toPlateModel(plateRepository.save(plateEntity));
    }

    @Override
    public Page<PlateModel> getPlatesByPage(Long categoryId, Long restaurantId, int page, int size, String property, String sort) {
        Page<PlateEntity> result;
        if(sort.equals("ascending")) {
            result = plateRepository.findAllByIdCategory_IdAndIdRestaurant_Id( categoryId,restaurantId, PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.ASC,property))).orElseThrow(()-> new InfrastructureException("Plates not found", HttpStatus.NOT_FOUND));
        } else {
            result = plateRepository.findAllByIdCategory_IdAndIdRestaurant_Id( categoryId,restaurantId, PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.DESC,property))).orElseThrow(()-> new InfrastructureException("Plates not found", HttpStatus.NOT_FOUND));
        }
        return result.map(plateEntityMapper::toPlateModel);
    }

    @Override
    public String findCurrentUserId() {
        String securityCredentials = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return securityCredentials;
    }

}
