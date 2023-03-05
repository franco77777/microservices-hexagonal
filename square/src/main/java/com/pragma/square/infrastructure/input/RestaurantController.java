package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IRestaurantHandler;
import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.RestaurantPageDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import com.pragma.square.application.utils.RestaurantsPagesDto;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    System.out.println("soy context");
    System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
    System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());


        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RestaurantResponseDto>> getByUserId(@PathVariable("id") Long userId) {

        List<RestaurantResponseDto> result = restaurantHandler.getRestaurantsByUserId(userId);
       return ResponseEntity.ok(result);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{ownerId}")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody @Valid RestaurantRequestDto restaurant,@PathVariable("ownerId") Long ownerId) {
     Long UserId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
     if(ownerId == null) throw new InfrastructureException( "ownerId is null",HttpStatus.BAD_REQUEST);
     restaurant.setUserId(ownerId);
     RestaurantResponseDto response = restaurantHandler.saveRestaurant(restaurant);
     return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/pagination")
    public ResponseEntity<RestaurantsPagesDto<Page<RestaurantPageDto>>> getRestaurantsWhitPagination(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
        Page<RestaurantPageDto> restaurantsWhitPagination = restaurantHandler.getRestaurantsByPage(page, size,sort);
        return ResponseEntity.ok(new RestaurantsPagesDto<>(restaurantsWhitPagination.getSize(), restaurantsWhitPagination));
    }



}
