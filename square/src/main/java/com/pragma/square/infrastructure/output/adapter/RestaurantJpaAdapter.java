package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;
import com.pragma.square.infrastructure.exception.NoDataFoundException;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import com.pragma.square.infrastructure.security.RestaurantService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
private final IRestaurantRepository restaurantRepository;
private final IRestaurantEntityMapper restaurantEntityMapper;

private final RestaurantService service;


    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
      RestaurantEntity response = service.RestaurantSave(restaurantEntityMapper.toRestaurantEntity(restaurantModel));
      return restaurantEntityMapper.toRestaurantModel(response);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> response = restaurantRepository.findAll();
        if(response.isEmpty()) {throw new NoDataFoundException();}
        return restaurantEntityMapper.toRestaurantModelList(response);
    }

    @Override
    public List<RestaurantModel> getRestaurantsByUserId(Long userId) {
        List<RestaurantEntity> response = restaurantRepository.findByUserId(userId).orElseThrow(NoDataFoundException::new);

        return restaurantEntityMapper.toRestaurantModelList(response);
    }
}
