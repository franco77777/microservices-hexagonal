package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IRestaurantHandler;
import com.pragma.square.application.handler.RestaurantHandler;
import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/square/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantHandler restaurantHandler;
    private final IRestaurantRepository restaurantRepository;

    @GetMapping()
    public ResponseEntity<List<RestaurantResponseDto>> getAll() {

        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RestaurantResponseDto>> getByUserId(@PathVariable("id") Long userId) {

        List<RestaurantResponseDto> result = restaurantHandler.getRestaurantsByUserId(userId);
       return ResponseEntity.ok(result);
       // return ResponseEntity.ok(restaurantHandler.getRestaurantsByUserId(userId));
    }
    @PostMapping()
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody @Valid RestaurantRequestDto restaurant) {


     RestaurantResponseDto response = restaurantHandler.saveRestaurant(restaurant);
     return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
