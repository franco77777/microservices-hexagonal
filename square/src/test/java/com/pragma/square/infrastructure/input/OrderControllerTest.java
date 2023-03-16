package com.pragma.square.infrastructure.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.square.application.handler.IOrderHandler;
import com.pragma.square.application.request.ClientRequest;
import com.pragma.square.application.response.OrderResponseDto;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
   IOrderRepository orderRepository;
    @Mock
    IOrderHandler orderHandler;
    @InjectMocks
    OrderController orderController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void createShouldReturnOrderResponseDto() throws Exception {
        //given
        List<ClientRequest> clientRequestList = new ArrayList<>();
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(clientRequestList);

        //when
        when(orderHandler.create(clientRequestList))
                .thenReturn(orderResponseDto);
        MockHttpServletRequestBuilder request = post("/square/order")
                .contentType("application/json")
                .content(expectedJson);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void pageShouldReturnPageOfOrderResponseDto() throws Exception {
        //given
        Page<OrderResponseDto> orderResponseDtoPage = Mockito.mock(Page.class);
        List<OrderResponseDto> orders = new ArrayList<>();
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(1L);
        orders.add(orderResponseDto);
        Page<OrderResponseDto> pagedResponse = new PageImpl(orders);
        System.out.println("soy pagedResponse");
        System.out.println(pagedResponse.getSize());

        //when

        when(orderHandler.findByStatus(1,1,"ascending","pending","id"))
                .thenReturn(orderResponseDtoPage);
        orderController.page(1,1,"ascending","pending","id");
        MockHttpServletRequestBuilder request = get("/square/order")
                .contentType("application/json")
                .param("page", "1")
                .param("size", "1")
                .param("sort", "ascending")
                .param("status", "1")
                .param("property", "id");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateShouldReturnOrderResponseDto() throws Exception {
        //given
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(1L);
        orderResponseDto.setStatus("ready");

        //then
        when(orderHandler.updateStatus(1L,"ready"))
                .thenReturn(orderResponseDto);
        MockHttpServletRequestBuilder request = put("/square/order/1")
                .contentType("application/json")
                .param("status", "ready");

        //when
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.status").value("ready"));
    }

    @Test
    void updateToDeliveredShouldReturnOrderResponseDto() throws Exception {
        //given
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(1L);
        orderResponseDto.setStatus("delivered");

        //when
        when(orderHandler.updateToDelivered(1L))
                .thenReturn(orderResponseDto);
        MockHttpServletRequestBuilder request = put("/square/order/delivered/1")
                .contentType("application/json");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.status").value("delivered"));
    }

    @Test
    void deleteShouldReturnString() throws Exception {
        //given

        //when
        MockHttpServletRequestBuilder request = delete("/square/order");
        orderController.delete();

        //then
        verify(orderHandler, Mockito.times(1)).deleteOrder();
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("your order has been deleted"));
    }

}