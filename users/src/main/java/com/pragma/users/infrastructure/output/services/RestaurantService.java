package com.pragma.users.infrastructure.output.services;

import com.pragma.users.domain.model.Square.RestaurantModel;

import com.pragma.users.infrastructure.configuration.feingclient.RestaurantFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

   private final RestTemplate restTemplate;
   private final RestaurantFeignClient restaurantFeignClient;



//    public List<RestaurantModel> getAll() {
//        List<RestaurantModel> result = restTemplate.getForObject("http://localhost:8082/restaurant" , List.class);
//        return result;
//    }

//    public List<RestaurantModel> getByUserId( Long userId) {
//        List<RestaurantModel> result = restTemplate.getForObject("http://localhost:8082/restaurant/" + userId, List.class);
//        return result;
//    }

    public RestaurantModel createRestaurant(Long userId, RestaurantModel restaurant) {
        restaurant.setUserId(userId);
        RestaurantModel restaurantNew = restaurantFeignClient.createRestaurant(restaurant);
        return restaurantNew;
    }

    public List<RestaurantModel>getAll(){
        return restaurantFeignClient.getAll();
    }
    public List<RestaurantModel>getByUserId(Long userId){
        return restaurantFeignClient.getByUserId(userId);
    }
}
