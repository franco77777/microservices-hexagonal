package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IObjectHandler;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.Square.RestaurantModel;
import com.pragma.users.infrastructure.output.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    }







