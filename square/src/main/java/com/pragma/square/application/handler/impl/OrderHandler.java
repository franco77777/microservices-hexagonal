package com.pragma.square.application.handler.impl;

import com.pragma.square.application.handler.IOrderHandler;
import com.pragma.square.application.mapper.IClientRequestMapper;
import com.pragma.square.application.mapper.IOrderResponseMapper;
import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import com.pragma.square.domain.api.IOrderServicePort;
import com.pragma.square.domain.models.ClientRequestModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderHandler implements IOrderHandler {
    private final IClientRequestMapper clientRequestMapper;
    private final IOrderServicePort orderServicePort;
    private final IOrderResponseMapper orderResponseMapper;
    @Override
    public OrderResponseDto create(List<ClientRequest> clientRequestList) {
        List<ClientRequestModel> requests = clientRequestMapper.toModel(clientRequestList);
        return orderResponseMapper.toResponseDto(orderServicePort.create(requests));

    }

    @Override
    public Page<OrderResponseDto> findBySatus(int page, int size, String sort, String status,String property) {
        return orderServicePort.findBySatus(page, size, sort, status,property).map(orderResponseMapper::toResponseDto);
    }

    @Override
    public OrderResponseDto updateToPreparing(Long plateId) {
        return orderResponseMapper.toResponseDto(orderServicePort.updateToPreparing(plateId));
    }
}
