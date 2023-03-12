package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;

import com.pragma.square.infrastructure.utils.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
private final IRestaurantRepository restaurantRepository;
private final IRestaurantEntityMapper restaurantEntityMapper;
private final UserService userService;



    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity response = restaurantRepository.save(restaurantEntityMapper.toRestaurantEntity(restaurantModel));
      return restaurantEntityMapper.toRestaurantModel(response);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> response = restaurantRepository.findAll();
        if(response.isEmpty()) throw new InfrastructureException("no restaurants in this database", HttpStatus.NOT_FOUND);
        return restaurantEntityMapper.toRestaurantModelList(response);
    }

    @Override
    public List<RestaurantModel> getRestaurantsByUserId(Long userId) {
        List<RestaurantEntity> response = restaurantRepository.findByUserId(userId).orElseThrow(()-> new InfrastructureException("restaurants not found whit user id "+userId,HttpStatus.NOT_FOUND));

        return restaurantEntityMapper.toRestaurantModelList(response);
    }

    @Override
    public Page<RestaurantModel> getRestaurantsByPage(int page, int size, String sort) {

        Page<RestaurantEntity> pages ;
        if(sort.equals("ascending")){

            pages = restaurantRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.ASC,"name")));
        }else {
            pages = restaurantRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.DESC, "name")));
        }
        if(pages.isEmpty()) throw new InfrastructureException("no restaurants in this database", HttpStatus.NOT_FOUND);
        return pages.map(restaurantEntityMapper::toRestaurantModel);
    }

    @Override
    public String getRoleUser(Long userId) {
        return userService.getClientRole(userId);
    }
}
