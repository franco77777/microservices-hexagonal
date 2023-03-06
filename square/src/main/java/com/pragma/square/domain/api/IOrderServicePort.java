package com.pragma.square.domain.api;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Range;

import java.util.List;

public interface IOrderServicePort {
    OrderModel create(List<ClientRequestModel> requests);

    Page<OrderModel> findBySatus(int page, int size, String sort, String status,String property);

    OrderModel updateToPreparing(Long plateId);
}
