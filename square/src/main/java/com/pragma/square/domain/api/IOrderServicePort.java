package com.pragma.square.domain.api;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderServicePort {
    OrderModel create(List<ClientRequestModel> requests);


    Page<OrderModel> findByStatus(int page, int size, String sort, String status, String property);

    OrderModel updateStatus(Long plateId,String status);


    OrderModel updateToDelivered(Long orderId);

    void deleteOrder();
}
