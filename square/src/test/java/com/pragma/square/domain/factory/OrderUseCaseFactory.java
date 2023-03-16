package com.pragma.square.domain.factory;

import com.pragma.square.domain.models.*;

import java.util.ArrayList;
import java.util.List;


public class OrderUseCaseFactory {
    public static OrderModel getOrderModel() {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);
        OrderModel expected = new OrderModel();
        expected.setIdRestaurant(restaurantModel);
        expected.setStatus("preparing");
        return expected;
    }
    public static List<ClientRequestModel> getClientRequestModel() {
        List<ClientRequestModel> clientRequestModelList = new ArrayList<>();
        ClientRequestModel clientRequestModel = new ClientRequestModel();
        clientRequestModel.setPlateId(1L);
        clientRequestModel.setQuantity(1);
        ClientRequestModel clientRequestModel2 = new ClientRequestModel();
        clientRequestModel2.setPlateId(2L);
        clientRequestModel2.setQuantity(1);
        clientRequestModelList.add(clientRequestModel);
        clientRequestModelList.add(clientRequestModel2);

        return clientRequestModelList;
    }

    public static ClientModel getClientModel() {
        ClientModel clientModel = new ClientModel();
        clientModel.setMobile("+7777777");
        clientModel.setEmail("hzdkv@example.com");
        return clientModel;
    }

}
