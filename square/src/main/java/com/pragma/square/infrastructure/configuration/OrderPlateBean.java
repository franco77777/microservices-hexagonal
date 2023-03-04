//package com.pragma.square.infrastructure.configuration;
//
//import com.pragma.square.domain.api.IOrderPlateSP;
//import com.pragma.square.domain.spi.IOrderPlatePP;
//import com.pragma.square.domain.usecase.OrderPlateUC;
//import com.pragma.square.infrastructure.output.adapter.OrderPlateJA;
//import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
//import com.pragma.square.infrastructure.output.mapper.IOrderPlateEntityMapper;
//import com.pragma.square.infrastructure.output.repository.IOrderPlateRepository;
//import com.pragma.square.infrastructure.output.repository.IOrderRepository;
//import com.pragma.square.infrastructure.output.repository.IPlateRepository;
//import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class OrderPlateBean {
//    private final IOrderPlateEntityMapper orderPlateEntityMapper;
//    private final IOrderEntityMapper orderEntityMapper;
//    private final IRestaurantRepository restaurantRepository;
//    private final IPlateRepository plateRepository;
//    private final IOrderPlateRepository orderPlateRepository;
//    private final IOrderRepository orderRepository;
//    @Bean
//    public IOrderPlatePP orderPlatePP() {
//        return new OrderPlateJA(orderPlateEntityMapper, orderEntityMapper, restaurantRepository, plateRepository, orderPlateRepository,orderRepository);
//    }
//    @Bean
//    public IOrderPlateSP orderPlateSP() {
//        return new OrderPlateUC(orderPlatePP());
//    }
//}
