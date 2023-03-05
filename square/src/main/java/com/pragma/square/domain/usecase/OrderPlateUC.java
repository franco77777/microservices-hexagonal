//package com.pragma.square.domain.usecase;
//
//import com.pragma.square.domain.api.IOrderPlateSP;
//import com.pragma.square.domain.models.OrderModel;
//import com.pragma.square.domain.models.OrderPlateModel;
//import com.pragma.square.domain.spi.IOrderPlatePP;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Date;
//
//public class OrderPlateUC implements IOrderPlateSP {
//    private final IOrderPlatePP orderPlatePP;
//
//    public OrderPlateUC(IOrderPlatePP orderPlatePP) {
//        this.orderPlatePP = orderPlatePP;
//    }
//
//    @Override
//    public OrderPlateModel create(Integer quantity, Long idPlate) {
//        String UserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
//        OrderModel orderModel = new OrderModel();
//        orderModel.setOrderDate(new Date());
//        orderModel.setIdClient(Long.parseLong(UserId));
//        orderModel.setStatus("pending");
//        return orderPlatePP.create(quantity, idPlate,orderModel);
//    }
//}
