package com.pragma.square.application.handler;

import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto create(List<ClientRequest> clientRequestList);
}
