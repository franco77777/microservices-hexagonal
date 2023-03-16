package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.*;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.*;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateQuantityEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.*;
import com.pragma.square.infrastructure.utils.UserService;
import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.domain.messages.*;
import com.whatsapp.api.domain.messages.response.MessageResponse;
import com.whatsapp.api.domain.templates.type.ComponentType;
import com.whatsapp.api.domain.templates.type.LanguageType;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;


@RequiredArgsConstructor

public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IEmployeeRepository employeeRepository;
    private final IPlateQuantityEntityMapper plateQuantityEntityMapper;
    private final IPlateQuantityRepository plateQuantityRepository;
    private final UserService userService;

    @Override
    public OrderModel create(OrderModel order) {
        OrderEntity result = orderEntityMapper.toEntity(order);
        return orderEntityMapper.toModel(orderRepository.save(result));
    }

    @Override
    public PlateModel findPlateById(Long plateId) {
        PlateEntity plateEntity = plateRepository.findById(plateId).orElseThrow(()->new InfrastructureException("Plate id: "+ plateId + " not found", HttpStatus.BAD_REQUEST));
        return plateEntityMapper.toPlateModel(plateEntity);
    }

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id).orElseThrow(()->new InfrastructureException("restaurant id: "+ id + " not found", HttpStatus.BAD_REQUEST));
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public Boolean orderExists() {
        String idClient = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return orderRepository.existsByIdClient(Long.parseLong(idClient));
    }

    @Override
    public Long findEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new InfrastructureException("you are not an employee",HttpStatus.UNAUTHORIZED));
        return employeeEntity.getRestaurantId();
    }

    @Override
    public Page<OrderModel> findByStatus(Long restaurantId, int page, int size, String sort, String status,String property) {
        Page<OrderEntity> result;
        if(sort.equals("ascending")){
            result = orderRepository.findAllByIdRestaurant_IdAndStatus( restaurantId,status, PageRequest.of(page, size)
                    .withSort(Sort.by(Sort.Direction.ASC,property))).orElseThrow(()-> new InfrastructureException("no orders found", HttpStatus.BAD_REQUEST));
        } else {
            result = orderRepository.findAllByIdRestaurant_IdAndStatus( restaurantId,status, PageRequest.of(page, size)
                    .withSort(Sort.by(Sort.Direction.DESC,property))).orElseThrow(()-> new InfrastructureException("no orders found", HttpStatus.BAD_REQUEST));
        }
        return result.map(orderEntityMapper::toModel);
    }

    @Override
    public OrderModel findOrderById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(()->new InfrastructureException("order id: "+ orderId + " not found", HttpStatus.BAD_REQUEST));
        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public OrderModel updateOrder(OrderModel order) {
        OrderEntity resultUpdate = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toModel(resultUpdate);
    }

    @Override
    public ClientModel findClient(Long orderClientId) {
        return userService.getClient(orderClientId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public PlateQuantityModel createPlateQuantity(PlateQuantityModel quantity) {
        PlateQuantityEntity result = plateQuantityEntityMapper.toEntity(quantity);
        PlateQuantityEntity response = plateQuantityRepository.save(result);
        return plateQuantityEntityMapper.toModel(response);
    }

    @Override
    public OrderModel findOrderByUserId(Long userIdLogged) {
        OrderEntity orderEntity = orderRepository.findByIdClient(userIdLogged).orElseThrow(()->new InfrastructureException("the current user no have orders", HttpStatus.NOT_FOUND));
        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public String findCurrentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    }

    @Override
    public void sendMessageReady(String clientPhone, Long id) {
        if(clientPhone.equals("7777777") )return;
        try{
            WhatsappApiFactory factory = WhatsappApiFactory.newInstance("EAADEmIApxS0BAMA14Yse0RpnNK87tslCVvUZCRvQA5VZBDFoaszwpXZBImzHEm8krdGL1MoUZAvS2HTWZCfUZAiNragxZBNI59SqnLp508YSyDbVKSlA0zOZCqhgC1v43XEfOZAzPgv98eMu863ZCds8MNKUaB2BMh8TwFvZBIm9j5S2Bu71mvMPBGwfgNwgN7681ZCY32LPdoiMEwZDZD");
            WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
            var message = Message.MessageBuilder.builder()
                    .setTo(clientPhone)
                    .buildTemplateMessage(
                            new TemplateMessage()
                                    .setLanguage(new Language(LanguageType.EN))
                                    .setName("order_alert")
                                    .addComponent(
                                            new Component(ComponentType.BODY)
                                                    .addParameter(new TextParameter(Long.toString(id)))
                                    ));
            MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("108928785480520", message);
            System.out.println(messageResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void sendMessageRequestFail(String clientPhone,String email) {
        if(clientPhone.equals("7777777") )return;
        try{
            WhatsappApiFactory factory = WhatsappApiFactory.newInstance("EAADEmIApxS0BAMA14Yse0RpnNK87tslCVvUZCRvQA5VZBDFoaszwpXZBImzHEm8krdGL1MoUZAvS2HTWZCfUZAiNragxZBNI59SqnLp508YSyDbVKSlA0zOZCqhgC1v43XEfOZAzPgv98eMu863ZCds8MNKUaB2BMh8TwFvZBIm9j5S2Bu71mvMPBGwfgNwgN7681ZCY32LPdoiMEwZDZD");
            WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
            var message = Message.MessageBuilder.builder()
                    .setTo(clientPhone)
                    .buildTemplateMessage(
                            new TemplateMessage()
                                    .setLanguage(new Language(LanguageType.EN))
                                    .setName("order_cancelled2")
                                    .addComponent(
                                            new Component(ComponentType.BODY)
                                                    .addParameter(new TextParameter(email))
                                    ));
            MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("108928785480520", message);
            System.out.println(messageResponse);
        }catch (Exception e){

            System.out.println(e.getMessage());

        }

    }


}
