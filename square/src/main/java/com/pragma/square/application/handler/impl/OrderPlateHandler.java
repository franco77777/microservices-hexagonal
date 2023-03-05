//package com.pragma.square.application.handler.impl;
//
//import com.pragma.square.application.handler.IOrderPlateHandler;
//import com.pragma.square.application.mapper.IOrderPlateResponseMapper;
//import com.pragma.square.application.response.OrderPlateResponseDto;
//import com.pragma.square.domain.api.IOrderPlateSP;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Transactional
//@Service
//public class OrderPlateHandler implements IOrderPlateHandler {
//    private final IOrderPlateResponseMapper orderPlateResponseMapper;
//    private final IOrderPlateSP orderPlateSP;
//    @Override
//    public OrderPlateResponseDto create(Integer quantity, Long idPlate) {
//        return orderPlateResponseMapper.toResponseDto(orderPlateSP.create(quantity, idPlate));
//    }
//}
