package com.pragma.square.domain.usecase;

import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.models.ClientRequestModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    IOrderPersistencePort orderPersistencePort;
    @InjectMocks
    OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(auth.getCredentials()).thenReturn("1");
    }

    @Test
    void shouldCreateOrder() {
        //given
        List<ClientRequestModel> clientRequestModelList = new ArrayList<>();
        ClientRequestModel clientRequestModel = new ClientRequestModel();
        clientRequestModel.setPlateId(1L);
        clientRequestModel.setQuantity(1);
        ClientRequestModel clientRequestModel2 = new ClientRequestModel();
        clientRequestModel2.setPlateId(2L);
        clientRequestModel2.setQuantity(1);
        clientRequestModelList.add(clientRequestModel);
        clientRequestModelList.add(clientRequestModel2);
        List<PlateModel> plateModelList = new ArrayList<>();
        Set<Long> plateIds = new HashSet<>();
        PlateModel plateModel = new PlateModel();
        OrderModel orderModel = new OrderModel();
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);
        plateModel.setId(1L);
        plateModel.setIdRestaurant(restaurantModel);
        plateModelList.add(plateModel);


        Mockito.when(orderPersistencePort.orderExists())
                .thenReturn(false);
        Mockito.when(orderPersistencePort.findPlateById(anyLong()))
                .thenReturn(plateModel);
        Mockito.when(orderPersistencePort.findRestaurantById(anyLong()))
                .thenReturn(restaurantModel);
        Mockito.when(orderPersistencePort.create(Mockito.any()))
                .thenReturn(orderModel);
        OrderModel result = orderUseCase.create(clientRequestModelList);

        //then
        Assertions.assertEquals(orderModel, result);
    }

    @Test
    void createShouldThrowExceptionIfOrderAlreadyExistsIsFalse(){
        //given
        //when
        Mockito.when(orderPersistencePort.orderExists())
                .thenReturn(true);
        //then
        Assertions.assertThrows(DomainException.class, () -> {
            orderUseCase.create(new ArrayList<>());
        });
    }

    @Test
    void createShouldThrowExceptionIfPlateIdNotExist(){
        //given
        List<ClientRequestModel> clientRequestModelList = new ArrayList<>();
        ClientRequestModel clientRequestModel = new ClientRequestModel();
        clientRequestModel.setPlateId(null);
        clientRequestModel.setQuantity(1);
        clientRequestModelList.add(clientRequestModel);

        //when
        Mockito.when(orderPersistencePort.orderExists())
                .thenReturn(false);
        //then
        Assertions.assertThrows(DomainException.class, () -> {
            orderUseCase.create(clientRequestModelList);
        });
    }
@Test
    void createShouldThrowExceptionIfQuantityNotExist(){
        //given
        List<ClientRequestModel> clientRequestModelList = new ArrayList<>();
        ClientRequestModel clientRequestModel = new ClientRequestModel();
        clientRequestModel.setPlateId(1L);
        clientRequestModel.setQuantity(null);
        clientRequestModelList.add(clientRequestModel);

        //when
        Mockito.when(orderPersistencePort.orderExists())
                .thenReturn(false);

        //then
        Assertions.assertThrows(DomainException.class, () -> {
            orderUseCase.create(clientRequestModelList);
        });
    }


    @Test
    void findByStatusShouldReturnPageOrderModel(){
        //given
        OrderModel orderModel = new OrderModel();
        OrderModel orderModel2 = new OrderModel();
        int page = 1;
        int size = 2;
        String sort = "ascending";
        String status= "pending";
        String property = "id";
        Long restaurantId = 1L;
        Page<OrderModel> expected =  new PageImpl<>(List.of(orderModel, orderModel2), pageable, 0);

        //when
        Mockito.when(orderPersistencePort.findEmployee())
                .thenReturn(restaurantId);
        Mockito.when(orderPersistencePort.findByStatus(restaurantId,page, size, sort, status, property))
                .thenReturn(Mock);
        Page<OrderModel> result = orderUseCase.findByStatus(page, size, sort, status, property);

        //then
        Assertions.assertEquals(expected, result);


    }

    @Test
    void updateStatus() {
    }

    @Test
    void updateToDelivered() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void currentEmployeeValidate() {
    }

    @Test
    void sendMessageReady() {
    }

    @Test
    void sendMessageRequestFail() {
    }
}