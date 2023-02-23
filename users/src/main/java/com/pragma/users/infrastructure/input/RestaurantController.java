package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IObjectHandler;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.Square.RestaurantModel;
import com.pragma.users.infrastructure.output.services.RestaurantService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/restaurant")
@CrossOrigin(origins = "*")

public class RestaurantController {
@Autowired
    RestaurantService restaurantService;
   @CircuitBreaker(name = "squareCB", fallbackMethod = "fallbackGetRestaurant")
   @GetMapping("/{id}")
    public ResponseEntity<List<RestaurantModel>> getByUserId(@PathVariable("id") Long userId){
       List<RestaurantModel> restaurants = restaurantService.getByUserId(userId);
           return ResponseEntity.ok(restaurants);
       }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantModel>> getAll(){
        List<RestaurantModel> restaurants = restaurantService.getAll();
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<RestaurantModel> saveRestaurant(@PathVariable("userId") Long userId, @RequestBody RestaurantModel restaurant) {

        RestaurantModel restaurantNew = restaurantService.createRestaurant(userId, restaurant);
        return ResponseEntity.ok(restaurantNew);
    }
    public ResponseEntity<List<RestaurantModel>>fallbackGetRestaurant(@PathVariable("id") Long userId, RuntimeException e){
       return new ResponseEntity("error in the database square whit the user " + userId, HttpStatus.OK);
    }
    }







