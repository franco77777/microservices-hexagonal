package com.pragma.square.infrastructure.security;

import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final IRestaurantRepository restaurantRepository;
    public RestaurantEntity RestaurantSave(RestaurantEntity restaurantEntity) {
        var restaurant= RestaurantEntity.builder()
                .name(restaurantEntity.getName())
                .address(restaurantEntity.getAddress())
                .telephone(restaurantEntity.getTelephone())
                .url(restaurantEntity.getUrl())
                .userId(restaurantEntity.getUserId())
                .nit(restaurantEntity.getNit())
                .build();
        return restaurantRepository.save(restaurant);
    }

}
