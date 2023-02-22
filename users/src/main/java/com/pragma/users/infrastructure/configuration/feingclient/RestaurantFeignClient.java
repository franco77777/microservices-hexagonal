package com.pragma.users.infrastructure.configuration.feingclient;



import com.pragma.users.domain.model.Square.RestaurantModel;

import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "square", url = "localhost:8082/api/restaurant")

public interface RestaurantFeignClient {
    @PostMapping()
    RestaurantModel createRestaurant(@RequestBody RestaurantModel restaurant);
    @GetMapping()
    List<RestaurantModel> getAll();

    @GetMapping("/{id}")
    List<RestaurantModel> getByUserId(@PathVariable("id") Long userId) ;
}

