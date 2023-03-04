package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IOrderHandler;
import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import com.pragma.square.infrastructure.output.entity.OrderEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/square/order")
public class OrderController {
    private final IRestaurantRepository restaurantRepository;
    private final IOrderRepository orderRepository;
    private final IOrderHandler orderHandler;
    @PostMapping
    public ResponseEntity<OrderResponseDto>create(@RequestBody List<ClientRequest> clientRequestList){
        OrderResponseDto response = orderHandler.create(clientRequestList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    private ResponseEntity< List<OrderEntity>> getOrders() {
        List<OrderEntity> OrderEntity = orderRepository.findAll();
        return new ResponseEntity<>(OrderEntity, HttpStatus.CREATED);
    }

}
