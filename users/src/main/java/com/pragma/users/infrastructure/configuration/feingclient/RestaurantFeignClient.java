package com.pragma.users.infrastructure.configuration.feingclient;



import com.pragma.users.domain.model.Square.RestaurantModel;

import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "square-service")

public interface RestaurantFeignClient {
    @PostMapping("/square/restaurant")
    RestaurantModel createRestaurant(@RequestBody RestaurantModel restaurant);
    @GetMapping("/square/restaurant")
    List<RestaurantModel> getAll();

    @GetMapping("/square/restaurant/{id}")
    List<RestaurantModel> getByUserId(@PathVariable("id") Long userId) ;
}

