package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.IOrderServicePort;
import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.*;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }


    @Override
    public OrderModel create(List<ClientRequestModel> requests) {
        String idClient = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Boolean orderAlreadyExists = orderPersistencePort.orderExists();
        if(orderAlreadyExists) throw new DomainException("Order already exists",HttpStatus.BAD_REQUEST);
        List<PlateModel> plates = platesForTheOrder(requests);
        List<PlateQuantityModel> quantity = platesQuantityForTheOrder(requests);
        RestaurantModel restaurant = orderPersistencePort.findRestaurantById(plates.get(0).getIdRestaurant().getId());
        OrderModel order = new OrderModel();
        order.setIdClient(Long.parseLong(idClient));
        order.setOrderDate(new Date());
        order.setStatus("pending");
        order.setPlates(plates);
        order.setQuantity(quantity);
        order.setIdRestaurant(restaurant);
        return orderPersistencePort.create(order);
    }

    @Override
    public Page<OrderModel> findByStatus(int page, int size, String sort, String status,String property) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long employeeId = Long.parseLong(currentUserId);
        if(!status.matches("pending|preparing|ready")) throw new DomainException("Invalid status, must be: pending|preparing|ready", HttpStatus.BAD_REQUEST);
        if(!property.matches("id|orderDate|idClient|idChef")) throw new DomainException("Invalid property, must be: id|orderDate|idClient|idChef", HttpStatus.BAD_REQUEST);
        if(!sort.matches("ascending|descending"))  throw new DomainException("Invalid sort, must be: ascending|descending", HttpStatus.BAD_REQUEST);
        Long restaurantId = orderPersistencePort.findEmployee(employeeId);
        return orderPersistencePort.findByStatus(restaurantId, page, size, sort, status,property);
    }
    @Override
    public OrderModel updateStatus(Long orderId,String status) {
        if(!status.matches("preparing|ready|delivered")) throw new DomainException("Invalid status, must be : preparing|ready", HttpStatus.BAD_REQUEST);
     return orderPersistencePort.updateOrder(currentEmployeeValidate(orderId,status));
    }

    @Override
    public OrderModel updateToDelivered(Long orderId) {
        return orderPersistencePort.updateOrder(currentEmployeeValidate(orderId,"delivered"));
    }

    @Override
    public void deleteOrder(Long orderId) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long userIdLogged = Long.parseLong(currentUserId);
        OrderModel order = orderPersistencePort.findOrderById(orderId);
        Long userIdOfOrder = order.getIdClient();
        if(!userIdOfOrder.equals(userIdLogged)) throw new DomainException("You can't delete this order",HttpStatus.UNAUTHORIZED);
        String clientPhone = orderPersistencePort.findClientPhone(order.getIdClient());
        String phoneTransform = clientPhone.substring(1);
        if(!order.getStatus().equals("pending")){
            //sendMessageRequestFail(phoneTransform);
            throw new DomainException("The order has been taken, cannot be cancelled", HttpStatus.BAD_REQUEST);
        }
        orderPersistencePort.deleteOrder(orderId);
    }

    public OrderModel currentEmployeeValidate(Long orderId,String status) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long employeeId = Long.parseLong(currentUserId);
        Long restaurantIdOfEmployee = orderPersistencePort.findEmployee(employeeId);
        OrderModel order = orderPersistencePort.findOrderById(orderId);
        Long restaurantIdOfOrder = order.getIdRestaurant().getId();
        if(!Objects.equals(restaurantIdOfEmployee, restaurantIdOfOrder)) {
            throw new DomainException("You don't have access to this order", HttpStatus.UNAUTHORIZED);
        }
        if(status.equals("preparing") && !Objects.equals(order.getStatus(), "pending")) {
            throw new DomainException("The order must be pending to change it to preparing", HttpStatus.BAD_REQUEST);
        }
        if(status.equals("ready") && !Objects.equals(order.getStatus(), "preparing")) {
            throw new DomainException("The order must be preparing to change it to ready", HttpStatus.BAD_REQUEST);
        }
        if(status.equals("delivered") && !Objects.equals(order.getStatus(), "ready")) {
            throw new DomainException("The order must be ready to change it to delivered", HttpStatus.BAD_REQUEST);
        }
        if(status.equals("ready")) {
            String clientPhone = orderPersistencePort.findClientPhone(order.getIdClient());
            String phoneTransform = clientPhone.substring(1);
            System.out.println("soy number");
            System.out.println(clientPhone);
            //sendMessageReady(phoneTransform,order.getId());
        }
        order.setStatus(status);
        order.setIdChef(employeeId);
        return order;
    }



    public void sendMessageReady(String number,Long idOrder) {
        if(number.equals("7777777")) {return;}
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v16.0/108928785480520/messages"))
                    .header("Authorization", "Bearer EAADEmIApxS0BAI23iSUA8hKqi0NXv58UniJSErM8G0l65CkKbUDlmqoHOOHr8DJssQMb5loPEklk2nabrZApz1TO8ZBrW9zJsOllpNGocYAmyZC0T5xfBslajfn0JZCKzaDO0noZBAc6cXDBfubYqMDjOiV26EaxRvAkxZBZCEQ5kPLEQWfSdUAu9JZCA6evvXqYzRBnIDrPsQZDZD")
                    .header("Content-Type", "application/json")
                    //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"Hi, your order: "+idOrder+" is ready\" } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageRequestFail(String number) {
        if(number.equals("7777777")) {return;}
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v16.0/108928785480520/messages"))
                    .header("Authorization", "Bearer EAADEmIApxS0BAI23iSUA8hKqi0NXv58UniJSErM8G0l65CkKbUDlmqoHOOHr8DJssQMb5loPEklk2nabrZApz1TO8ZBrW9zJsOllpNGocYAmyZC0T5xfBslajfn0JZCKzaDO0noZBAc6cXDBfubYqMDjOiV26EaxRvAkxZBZCEQ5kPLEQWfSdUAu9JZCA6evvXqYzRBnIDrPsQZDZD")
                    .header("Content-Type", "application/json")
                    //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"Sorry, your order is already in preparation and cannot be cancelled\" } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<PlateModel> platesForTheOrder(List<ClientRequestModel> requests) {
        List<PlateModel> plates = new ArrayList<>();
        Set<Long> restaurantId= new HashSet<>();
        for (ClientRequestModel order : requests) {
            if(order.getPlateId() == null) throw new DomainException("plateId can't be null and must be a number", HttpStatus.BAD_REQUEST);
            if(order.getQuantity() == null) throw new DomainException("quantity can't be null and must be a number", HttpStatus.BAD_REQUEST);
            PlateModel plateFound = orderPersistencePort.findPlateById(order.getPlateId());
            if(plateFound.getActive().equals(false)) throw new DomainException("plate id: "+plateFound.getId()+" is not active", HttpStatus.BAD_REQUEST);
            restaurantId.add(plateFound.getIdRestaurant().getId());
            //plateFound.setQuantity(order.getQuantity());
            plates.add(plateFound);
        }
        if(restaurantId.size()>1)throw new DomainException("Dishes must be from a single restaurant", HttpStatus.BAD_REQUEST);
        return plates;
    }

    private List<PlateQuantityModel> platesQuantityForTheOrder(List<ClientRequestModel> requests){
        List<PlateQuantityModel> platesQuantity = new ArrayList<>();
        for (ClientRequestModel order : requests) {
            PlateQuantityModel quantity = new PlateQuantityModel();
            quantity.setPlateId(order.getPlateId());
            quantity.setQuantity(order.getQuantity());
            platesQuantity.add( orderPersistencePort.createPlateQuantity(quantity));

        }
        return platesQuantity;
    }


}
