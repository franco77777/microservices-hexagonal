package com.pragma.square.application.handler;

import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto create(List<ClientRequest> clientRequestList);
    Page<OrderResponseDto> findByStatus(int page, int size, String sort, String status, String property );

    OrderResponseDto updateStatus(Long plateId,String status);


    OrderResponseDto updateToDelivered(Long orderId);

    void deleteOrder(Long orderId);
}
