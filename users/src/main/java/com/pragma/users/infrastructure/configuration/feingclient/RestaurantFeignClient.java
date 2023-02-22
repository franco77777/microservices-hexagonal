package com.pragma.users.infrastructure.configuration.feingclient;



import com.pragma.users.domain.model.Square.RestaurantModel;

import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "square-service")

public interface RestaurantFeignClient {
    @PostMapping("/api/restaurant")
    RestaurantModel createRestaurant(@RequestBody RestaurantModel restaurant);
    @GetMapping("/api/restaurant")
    List<RestaurantModel> getAll();

    @GetMapping("/{id}")
    List<RestaurantModel> getByUserId(@PathVariable("id") Long userId) ;
}

