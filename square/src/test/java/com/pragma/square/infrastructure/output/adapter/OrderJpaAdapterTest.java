package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.ClientModel;
import com.pragma.square.domain.models.OrderModel;
import com.pragma.square.domain.models.PlateModel;
import com.pragma.square.domain.models.PlateQuantityModel;
import com.pragma.square.domain.models.RestaurantModel;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import com.pragma.square.infrastructure.output.entity.OrderEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.PlateQuantityEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.mapper.IOrderEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IPlateQuantityEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IPlateQuantityRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import com.pragma.square.infrastructure.utils.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderJpaAdapterTest {
    @Mock
    IPlateRepository plateRepository;
    @Mock
    IPlateEntityMapper plateEntityMapper;
    @Mock
    IRestaurantRepository restaurantRepository;
    @Mock
 IRestaurantEntityMapper restaurantEntityMapper;
    @Mock
 IOrderRepository orderRepository;
    @Mock
 IOrderEntityMapper orderEntityMapper;
    @Mock
 IEmployeeRepository employeeRepository;
    @Mock
IPlateQuantityEntityMapper plateQuantityEntityMapper;
    @Mock
 IPlateQuantityRepository plateQuantityRepository;
    @Mock
UserService userService;
    @InjectMocks
    OrderJpaAdapter orderJpaAdapter;

//////////////////////////////////////// <-- CREATE --> ///////////////////////////////

    @Test
    void createShouldReturnOrderModel() {
        //given
        OrderEntity entity = new OrderEntity();
        OrderModel orderModel = new OrderModel();


        //when
        when(orderEntityMapper.toEntity(any(OrderModel.class)))
                .thenReturn(entity);
        when(orderRepository.save(any(OrderEntity.class)))
                .thenReturn(entity);
        when(orderEntityMapper.toModel(any(OrderEntity.class)))
                .thenReturn(orderModel);
        OrderModel result = orderJpaAdapter.create(orderModel);

        //then
        assertEquals(orderModel,result);

    }

    //////////////////////////////////////// <-- FIND PLATE BY ID --> ///////////////////////////////

    @Test
    void findPlateById() {
        //given
        PlateEntity plate = new PlateEntity();
        Optional <PlateEntity> plateEntity = Optional.of(plate);
        PlateModel expected = new PlateModel();

        //when
        when(plateRepository.findById(anyLong()))
                .thenReturn(plateEntity);
        when(plateEntityMapper.toPlateModel(any(PlateEntity.class)))
                .thenReturn(expected);
        PlateModel result = orderJpaAdapter.findPlateById(1L);

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- FIND RESTAURANT BY ID --> ///////////////////////////////
    @Test
    void findRestaurantByIdShouldReturnRestaurantModel() {
        //given
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        RestaurantModel expected = new RestaurantModel();
        Optional<RestaurantEntity> restaurantOptional = Optional.of(restaurantEntity);

        //when
        when(restaurantRepository.findById(anyLong()))
                .thenReturn(restaurantOptional);
        when(restaurantEntityMapper.toRestaurantModel(any(RestaurantEntity.class)))
                .thenReturn(expected);
        RestaurantModel result = orderJpaAdapter.findRestaurantById(1L);

        //
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- ORDER EXISTS --> ///////////////////////////////
    @Test
    void orderExistsShouldReturnBoolean() {
        //given
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        //when
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.getCredentials()).thenReturn("1");
        when(orderRepository.existsByIdClient(anyLong()))
                .thenReturn(true);
        Boolean result = orderJpaAdapter.orderExists();

        //then
        assertEquals(true, result);
    }
    //////////////////////////////////////// <-- FIND EMPLOYEE --> ///////////////////////////////
    @Test
    void findEmployeeShouldReturnLong() {
        //given
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setEmployeeId(1L);
        employee.setRestaurantId(1L);
        Optional<EmployeeEntity> employeeOptional = Optional.of(employee);
        Long expected = 1L;

        //when
        when(employeeRepository.findByEmployeeId(1L))
                .thenReturn(employeeOptional);
        Long result = orderJpaAdapter.findEmployee(1L);

        //then
        assertEquals(expected, result);

        }
    //////////////////////////////////////// <-- FIND BY STATUS --> ///////////////////////////////
    @Test
    void findByStatusShouldReturnPageOrderModel() {
        //given
        Page<OrderEntity> page = new PageImpl<>(new ArrayList<>());
        OrderModel orderModel = new OrderModel();
        Page<OrderModel> expected = new PageImpl<>(new ArrayList<>());

        //when
        when(orderRepository.findAllByIdRestaurant_IdAndStatus(anyLong(),any(),any()))
                .thenReturn(Optional.of(page));
        Page<OrderModel> result = orderJpaAdapter.findByStatus(1L,1,1,"ascending","pending","id");

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- FIND BY ORDER ID --> ///////////////////////////////
    @Test
    void findOrderByIdShouldReturnOrderModel() {
        //given
        OrderEntity order = new OrderEntity();
        OrderModel expected = new OrderModel();

        //when
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));
        when(orderEntityMapper.toModel(any(OrderEntity.class)))
                .thenReturn(expected);
        OrderModel result = orderJpaAdapter.findOrderById(1L);

        //then
        assertEquals(expected, result);

    }
    //////////////////////////////////////// <-- UPDATE ORDER --> ///////////////////////////////
    @Test
    void updateOrderShouldReturnOrderModel() {
        //given
        OrderEntity order = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderModel expected = new OrderModel();
        OrderModel expected2 = new OrderModel();

        //when
        when(orderRepository.save(any(OrderEntity.class)))
                .thenReturn(order);
        when(orderEntityMapper.toEntity(any(OrderModel.class)))
                .thenReturn(order);
       when(orderEntityMapper.toModel(any(OrderEntity.class)))
               .thenReturn(expected);
       OrderModel result = orderJpaAdapter.updateOrder(expected2);

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- FIND CLIENT --> ///////////////////////////////
    @Test
    void findClientShouldReturnClientModel() {
        //given
        ClientModel expected = new ClientModel();

        //when
        when(userService.getClient(anyLong()))
                .thenReturn(expected);
        ClientModel result = orderJpaAdapter.findClient(1L);

    //then
        assertEquals(expected, result);
        }
    //////////////////////////////////////// <-- DELETE ORDER --> ///////////////////////////////
    @Test
    void deleteOrder() {
        //given
        //when
        orderJpaAdapter.deleteOrder(1L);

        //then
        verify(orderRepository,times(1)).deleteById(1L);
    }
    //////////////////////////////////////// <-- CREATE PLATE QUANTITY --> ///////////////////////////////
    @Test
    void createPlateQuantityShouldReturnPlateQuantityModel() {
        //given
        PlateQuantityEntity plateQuantityEntity = new PlateQuantityEntity();
        PlateQuantityEntity plateQuantityEntity2 = new PlateQuantityEntity();
        PlateQuantityModel plateQuantityModel = new PlateQuantityModel();
        PlateQuantityModel expected = new PlateQuantityModel();

        //when
        when(plateQuantityEntityMapper.toEntity(any(PlateQuantityModel.class)))
                .thenReturn(plateQuantityEntity);
        when(plateQuantityRepository.save(any(PlateQuantityEntity.class)))
                .thenReturn(plateQuantityEntity2);
        when(plateQuantityEntityMapper.toModel(any(PlateQuantityEntity.class)))
                .thenReturn(expected);
        PlateQuantityModel result = orderJpaAdapter.createPlateQuantity(plateQuantityModel);

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- FIND ORDER BY USER ID --> ///////////////////////////////
    @Test
    void findOrderByUserIdShouldReturnOrderModel() {
        //given
        OrderEntity orderEntity = new OrderEntity();
        OrderModel expected = new OrderModel();

        //when
        when(orderRepository.findByIdClient(anyLong()))
                .thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.toModel(any(OrderEntity.class)))
                .thenReturn(expected);
        OrderModel result = orderJpaAdapter.findOrderByUserId(anyLong());

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- FIND CURRENT USER ID --> ///////////////////////////////
    @Test
    void findCurrentUserIdShoudlReturnString() {
        //given
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        String expected = "1";

        //when
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.getCredentials()).thenReturn("1");
        String result = orderJpaAdapter.findCurrentUserId();

        //then
        assertEquals(expected, result);
    }
    //////////////////////////////////////// <-- SEND MESSAGE READY --> ///////////////////////////////
    @Test
    void sendMessageReady() {
        orderJpaAdapter.sendMessageReady("7777777",1L);
    }
    //////////////////////////////////////// <-- SEND MESSAGE REQUEST FAIL --> ///////////////////////////////
    @Test
    void sendMessageRequestFail() {
        orderJpaAdapter.sendMessageRequestFail("7777777","frannco");
          }
}