//package com.pragma.square.infrastructure.input;
//
//import com.pragma.square.application.handler.IOrderPlateHandler;
//import com.pragma.square.application.response.OrderPlateResponseDto;
//import com.pragma.square.domain.models.OrderModel;
//import com.pragma.square.infrastructure.exception.InfrastructureException;
//import com.pragma.square.infrastructure.output.entity.OrderEntity;
//import com.pragma.square.infrastructure.output.entity.OrderPlateEntity;
//import com.pragma.square.infrastructure.output.entity.PlateEntity;
//import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
//import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
//import com.pragma.square.infrastructure.output.mapper.IOrderPlateEntityMapper;
//import com.pragma.square.infrastructure.output.repository.IOrderPlateRepository;
//import com.pragma.square.infrastructure.output.repository.IOrderRepository;
//import com.pragma.square.infrastructure.output.repository.IPlateRepository;
//import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequiredArgsConstructor
//@RequestMapping("/square/orderPlate")
//public class OrderPlateController {
//    private final IOrderPlateEntityMapper orderPlateEntityMapper;
//    private final IOrderEntityMapper orderEntityMapper;
//    private final IRestaurantRepository restaurantRepository;
//    private final IPlateRepository plateRepository;
//    private final IOrderPlateRepository orderPlateRepository;
//    private final IOrderPlateHandler orderPlateHandler;
//    private final IOrderRepository orderRepository;
//    @PostMapping("/{idPlate}/{quantity}")
//    public ResponseEntity<OrderPlateResponseDto>create(@PathVariable("quantity") Integer quantity,@PathVariable("idPlate") Long idPlate){
//        OrderPlateResponseDto result = orderPlateHandler.create(quantity,idPlate);
////        String UserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
////
////        PlateEntity plateEntity = plateRepository.findById(idPlate).orElseThrow(()->new InfrastructureException("Plate not found", HttpStatus.BAD_REQUEST));
////        RestaurantEntity restaurantEntity = restaurantRepository.findById(plateEntity.getIdRestaurant().getId()).orElseThrow(()->new InfrastructureException("Restaurant not found",HttpStatus.BAD_REQUEST));
////
////        OrderEntity orderEntity = new OrderEntity();
////        orderEntity.setOrderDate(new Date());
////        orderEntity.setIdClient(Long.parseLong(UserId));
////        orderEntity.setStatus("pending");
////        orderEntity.setIdRestaurant(restaurantEntity);
////
////        OrderPlateEntity orderPlateEntity = OrderPlateEntity.builder()
////                .quantity(quantity)
////                .idOrder(orderRepository.save(orderEntity))
////                .idPlate(plateEntity)
////                .build();
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//}
