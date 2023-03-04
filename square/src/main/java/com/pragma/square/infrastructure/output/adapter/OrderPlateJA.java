//package com.pragma.square.infrastructure.output.adapter;
//
//import com.pragma.square.domain.models.OrderModel;
//import com.pragma.square.domain.models.OrderPlateModel;
//import com.pragma.square.domain.spi.IOrderPlatePP;
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
//
//@RequiredArgsConstructor
//public class OrderPlateJA implements IOrderPlatePP {
//    private final IOrderPlateEntityMapper orderPlateEntityMapper;
//    private final IOrderEntityMapper orderEntityMapper;
//    private final IRestaurantRepository restaurantRepository;
//    private final IPlateRepository plateRepository;
//    private final IOrderPlateRepository orderPlateRepository;
//    private final IOrderRepository orderRepository;
//    @Override
//    public OrderPlateModel create(Integer quantity, Long idPlate, OrderModel order) {
//        PlateEntity plateEntity = plateRepository.findById(idPlate).orElseThrow(()->new InfrastructureException("Plate not found", HttpStatus.BAD_REQUEST));
//        RestaurantEntity restaurantEntity = restaurantRepository.findById(plateEntity.getIdRestaurant().getId()).orElseThrow(()->new InfrastructureException("Restaurant not found",HttpStatus.BAD_REQUEST));
//        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
//        orderEntity.setIdRestaurant(restaurantEntity);
//
//        OrderPlateEntity orderPlateEntity = OrderPlateEntity.builder()
//                .quantity(quantity)
//                .idOrder(orderRepository.save(orderEntity))
//                .idPlate(plateEntity)
//                .build();
//
//        return orderPlateEntityMapper.toModel(orderPlateRepository.save(orderPlateEntity));
//    }
//}
