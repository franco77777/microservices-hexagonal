package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IOrderHandler;
import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import com.pragma.square.application.utils.PagesDto;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.OrderEntity;

import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PagesDto<Page<OrderResponseDto>>>findByStatus(
            @RequestParam int page, @RequestParam int size,@RequestParam String sort,@RequestParam String status,@RequestParam String property){
        Page<OrderResponseDto> paginationOfStates = orderHandler.findBySatus(page,size,sort,status,property);

        return ResponseEntity.ok(new PagesDto<>(paginationOfStates.getSize(), paginationOfStates));
    }
    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping("/{plateId}")
    public ResponseEntity<OrderResponseDto> updateToPreparing(@PathVariable Long plateId){
        OrderResponseDto orderResponseDto = orderHandler.updateToPreparing(plateId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity< List<OrderEntity>> getOrders() {
        List<OrderEntity> order = orderRepository.findAll();
        return ResponseEntity.ok(order);
    }

}
