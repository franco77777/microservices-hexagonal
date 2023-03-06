package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.IOrderServicePort;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderModel create(List<ClientRequestModel> requests) {
        String idClient = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Boolean orderAlreadyExists = orderPersistencePort.orderExists(Long.parseLong(idClient));
        if(orderAlreadyExists) throw new DomainException("Order already exists",HttpStatus.BAD_REQUEST);
        List<PlateModel> plates = new ArrayList<>();
        Set<Long> restaurantId= new HashSet<>();
        for (ClientRequestModel order : requests) {
            if(order.getPlateId() == null) throw new DomainException("plateId can't be null and must be a number", HttpStatus.BAD_REQUEST);
            if(order.getQuantity() == null) throw new DomainException("quantity can't be null and must be a number", HttpStatus.BAD_REQUEST);
            PlateModel plateFound = orderPersistencePort.findPlateById(order.getPlateId());
            restaurantId.add(plateFound.getIdRestaurant().getId());
            plateFound.setQuantity(order.getQuantity());
            plates.add(plateFound);
        }
        if(restaurantId.size()>1)throw new DomainException("Dishes must be from a single restaurant", HttpStatus.BAD_REQUEST);
        RestaurantModel restaurant = orderPersistencePort.findRestaurantById(plates.get(0).getIdRestaurant().getId());
        OrderModel order = new OrderModel();
        order.setIdClient(Long.parseLong(idClient));
        order.setOrderDate(new Date());
        order.setStatus("pending");
        order.setPlates(plates);
        order.setIdRestaurant(restaurant);
        return orderPersistencePort.create(order);
    }

    @Override
    public Page<OrderModel> findBySatus(int page, int size, String sort, String status,String property) {
        if(!status.matches("pending|preparing|ready")) throw new DomainException("Invalid status, must be: pending|preparing|ready", HttpStatus.BAD_REQUEST);
        if(!property.matches("id|orderDate|idClient|idChef")) throw new DomainException("Invalid property, must be: id|orderDate|idClient|idChef", HttpStatus.BAD_REQUEST);
        if(!sort.matches("ascending|descending")) throw new DomainException("Invalid sort, must be: ascending|descending", HttpStatus.BAD_REQUEST);
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long restaurantId = orderPersistencePort.findEmployee(Long.parseLong(currentUserId));
        Page<OrderModel> orderList = orderPersistencePort.findByStatus(restaurantId, page, size, sort, status,property);
        return orderList;
    }

    @Override
    public OrderModel updateToPreparing(Long plateId) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long restaurantId = orderPersistencePort.findEmployee(Long.parseLong(currentUserId));
        OrderModel order = orderPersistencePort.findOrderById(plateId);
        if(!Objects.equals(restaurantId, order.getIdRestaurant().getId())) {
            throw new InfrastructureException("You don't have access to this order", HttpStatus.UNAUTHORIZED);
        }
        order.setStatus("preparing");
        return orderPersistencePort.updateToPreparing(order);
    }
}