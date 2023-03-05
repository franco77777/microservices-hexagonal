package com.pragma.users.infrastructure.output.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SquareService  {

   private final SquareFeingClient squareFeingClient;
    public Boolean createRestaurantEmployee(Long ownerId,Long restaurantId,Long userId) {
        return squareFeingClient.createEmployee(ownerId,restaurantId,userId);
    }
}
