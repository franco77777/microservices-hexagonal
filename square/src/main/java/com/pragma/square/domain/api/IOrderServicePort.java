package com.pragma.square.domain.api;

import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;

import java.util.List;

public interface IOrderServicePort {
    OrderModel create(List<ClientRequestModel> requests);
}
