package com.pragma.square.application.handler;

import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto create(List<ClientRequest> clientRequestList);
    Page<OrderResponseDto> findBySatus( int page,  int size,  String sort,  String status,String property );

    OrderResponseDto updateToPreparing(Long plateId);

    OrderResponseDto ready(Long plateId);
}
