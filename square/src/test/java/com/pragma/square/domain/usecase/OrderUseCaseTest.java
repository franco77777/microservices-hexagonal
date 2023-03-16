package com.pragma.square.domain.usecase;

import com.pragma.square.domain.exception.DomainException;
import com.pragma.square.domain.factory.OrderUseCaseFactory;
import com.pragma.square.domain.models.*;
import com.pragma.square.domain.spi.IOrderPersistencePort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.bouncycastle.math.ec.rfc8032.Ed25519.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    IOrderPersistencePort orderPersistencePort;
    @InjectMocks
    OrderUseCase orderUseCase;

  //  Authentication auth = Mockito.mock(Authentication.class);
//    SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        SecurityContextHolder.setContext(securityContext);
//    when(securityContext.getAuthentication()).thenReturn(auth);
//    when(auth.getCredentials()).thenReturn("1");


////////////////////////////////<--- CREATE --->///////////////////////////////////////////
    @Test
    void shouldCreateOrder() {
        //given
        List<ClientRequestModel> clientRequestModelList = OrderUseCaseFactory.getClientRequestModel();
        Set<Long> plateIds = new HashSet<>();
        PlateModel plateModel = new PlateModel();
        OrderModel orderModel = new OrderModel();
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(1L);
        plateModel.setId(1L);
        plateModel.setIdRestaurant(restaurantModel);
        plateModel.setActive(true);
        PlateQuantityModel plateQuantityModel = new PlateQuantityModel();

       //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.orderExists())
                .thenReturn(false);
        when(orderPersistencePort.findPlateById(anyLong()))
                .thenReturn(plateModel);
        when(orderPersistencePort.findRestaurantById(anyLong()))
                .thenReturn(restaurantModel);
        when(orderPersistencePort.create(any()))
                .thenReturn(orderModel);
        when(orderPersistencePort.createPlateQuantity(any(PlateQuantityModel.class)))
                .thenReturn(plateQuantityModel);
        OrderModel result = orderUseCase.create(clientRequestModelList);

        //then
        Assertions.assertEquals(orderModel, result);
    }

    @Test
    void createShouldThrowExceptionIfOrderAlreadyExistsIsFalse(){
        //given

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.orderExists())
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
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.orderExists())
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
        when(orderPersistencePort.findCurrentUserId())
            .thenReturn("1");
        when(orderPersistencePort.orderExists())
                .thenReturn(false);

        //then
        Assertions.assertThrows(DomainException.class, () -> {
            orderUseCase.create(clientRequestModelList);
        });
    }

    ////////////////////////////////<--- FIND BY STATUS --->///////////////////////////////////////////
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
        Page<OrderModel> expected =  new PageImpl<>(new ArrayList<>());


        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(restaurantId);
        when(orderPersistencePort.findByStatus(restaurantId,page, size, sort, status, property))
                .thenReturn(expected);
        Page<OrderModel> result = orderUseCase.findByStatus(page, size, sort, status, property);

        //then
        Assertions.assertEquals(expected, result);


    }

    @Test
    void findByStatusShouldThrowExceptionWhenStatusIsWrong(){
        //given
        int page = 1;
        int size = 2;
        String sort = "ascending";
        String status= "pendin";
        String property = "id";

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        final Throwable raisedException = catchThrowable(() -> orderUseCase.findByStatus(page, size, sort, status, property));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid status, must be: pending|preparing|ready");
    }

    @Test
    void findByStatusShouldThrowExceptionWhenPropertyIsWrong(){
        //given
        int page = 1;
        int size = 2;
        String sort = "ascending";
        String status= "pending";
        String property = "idSort";

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        final Throwable raisedException = catchThrowable(() -> orderUseCase.findByStatus(page, size, sort, status, property));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid property, must be: id|orderDate|idClient|idChef");

    }

    @Test()
    void findByStatusShouldThrowExceptionWhenSortIsWrong() throws Exception{
        //given
        int page = 1;
        int size = 2;
        String sort = "asc";
        String status= "pending";
        String property = "id";

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        final Throwable raisedException = catchThrowable(() -> orderUseCase.findByStatus(page, size, sort, status, property));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid sort, must be: ascending|descending");


    }

    ////////////////////////////////<--- UPDATE STATUS --->///////////////////////////////////////////
    @Test
    void updateStatusShouldChangeStatus() {
        //given
        Long orderId = 1L;
        String status = "ready";
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setId(1L);
        expected.setIdClient(1L);
        ClientModel clientModel = OrderUseCaseFactory.getClientModel();


        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(1L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        when(orderPersistencePort.updateOrder(any()))
                .thenReturn(expected);
        when(orderPersistencePort.findClient(expected.getIdClient()))
                .thenReturn(clientModel);
        OrderModel result = orderUseCase.updateStatus(orderId, status);

        //then
        assertEquals(expected, result);
        Mockito.verify(orderPersistencePort, times(1)).sendMessageReady("7777777",1L);


    }

    @Test
    void updateStatusShouldThrowExceptionWhenStatusIsWrong(){
        //given
        String status = "preparin";
        Long orderId = 1L;

        //then
        final Throwable raisedException = catchThrowable(() -> orderUseCase.updateStatus(orderId,status));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("Invalid status, must be : preparing|ready");
    }

    @Test
    void updateStatusShouldThrowExceptionWhenIsNotAnEmployee(){
        //given
        Long orderId = 1L;
        String status = "preparing";
        OrderModel expected = OrderUseCaseFactory.getOrderModel();

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(2L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        final Throwable raisedException = catchThrowable(() -> orderUseCase.updateStatus(orderId,status));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("You don't have access to this order");
    }
@Test
    void updateStatusShouldThrowExceptionWhenStatusPreparingDosNotAgree(){
        //given
        Long orderId = 1L;
        String status = "preparing";
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setStatus("preparing");

        //when
    when(orderPersistencePort.findCurrentUserId())
            .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(1L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        final Throwable raisedException = catchThrowable(() -> orderUseCase.updateStatus(orderId,status));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("The order must be pending to change it to preparing");
    }

    @Test
    void updateStatusShouldThrowExceptionWhenStatusReadyDosNotAgree(){
        //given
        Long orderId = 1L;
        String status = "ready";
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setStatus("pending");

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(1L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        final Throwable raisedException = catchThrowable(() -> orderUseCase.updateStatus(orderId,status));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("The order must be preparing to change it to ready");
    }

    @Test
    void updateStatusShouldThrowExceptionWhenStatusDeliveredDosNotAgree(){
        //given
        Long orderId = 1L;
        String status = "delivered";
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setStatus("preparing");

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(1L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        final Throwable raisedException = catchThrowable(() -> orderUseCase.updateStatus(orderId,status));

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("The order must be ready to change it to delivered");

    }



////////////////////////////////<--- UPDATE TO DELIVERED --->///////////////////////////////////////////

    @Test
    void updateToDeliveredShouldChangeStatus() {
        //given
        Long orderId = 1L;
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setStatus("ready");
        expected.setIdClient(1L);

        //then
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findEmployee(anyLong()))
                .thenReturn(1L);
        when(orderPersistencePort.findOrderById(orderId))
                .thenReturn(expected);
        when(orderPersistencePort.updateOrder(any()))
                .thenReturn(expected);
        OrderModel result = orderUseCase.updateToDelivered(orderId);

        //then
        assertEquals(expected, result);


    }
    ////////////////////////////////<--- DELETE ORDER --->///////////////////////////////////////////
    @Test
    void ShouldDeleteOrder() {
        //given
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setStatus("pending");
        expected.setIdClient(1L);
        expected.setId(1L);
        ClientModel clientModel = OrderUseCaseFactory.getClientModel();

        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findOrderByUserId(1L))
                .thenReturn(expected);
        when(orderPersistencePort.findClient(expected.getIdClient()))
                .thenReturn(clientModel);
        orderUseCase.deleteOrder();

        //then
        Mockito.verify(orderPersistencePort, times(1)).deleteOrder(1L);

    }

    @Test
    void deleteOrderShouldThrowExceptionWhenStatusIsWrong(){
        //given
        OrderModel expected = OrderUseCaseFactory.getOrderModel();
        expected.setId(1L);
        expected.setStatus("preparing");
        expected.setIdClient(1L);
        ClientModel clientModel = OrderUseCaseFactory.getClientModel();


        //when
        when(orderPersistencePort.findCurrentUserId())
                .thenReturn("1");
        when(orderPersistencePort.findOrderByUserId(1L))
                .thenReturn(expected);
        when(orderPersistencePort.findClient(expected.getIdClient()))
                .thenReturn(clientModel);
        final Throwable raisedException = catchThrowable(() -> orderUseCase.deleteOrder());

        //then
        assertThat(raisedException).isInstanceOf(DomainException.class)
                .hasMessageContaining("The order has been taken, cannot be cancelled");
        Mockito.verify(orderPersistencePort, times(1)).sendMessageRequestFail("7777777","hzdkv@example.com");
    }






}