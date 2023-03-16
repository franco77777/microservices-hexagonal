package com.pragma.square.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.square.application.handler.IRestaurantHandler;
import com.pragma.square.application.request.RestaurantRequestDto;
import com.pragma.square.application.response.PlateResponseDto;
import com.pragma.square.application.response.RestaurantPageDto;
import com.pragma.square.application.response.RestaurantResponseDto;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {
    @Mock
    IRestaurantHandler restaurantHandler;

    @InjectMocks
    RestaurantController restaurantController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    void getByUserIdShouldReturnRestaurantResponseDtoList() throws Exception {
        //given
        List<RestaurantResponseDto> responseList = new ArrayList<>();
        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        RestaurantResponseDto responseDto2 = new RestaurantResponseDto();
        responseList.add(responseDto);
        responseList.add(responseDto2);

        //when
        when(restaurantHandler.getRestaurantsByUserId(anyLong()))
                .thenReturn(responseList);
        MockHttpServletRequestBuilder request = get("/square/restaurant/1");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void createRestaurantShouldReturnRestaurantResponseDto() throws Exception {
        //given
        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        responseDto.setName("name");
        responseDto.setAddress("address");
        RestaurantRequestDto requestDto = new RestaurantRequestDto();
        requestDto.setName("name");
        requestDto.setAddress("address");
        requestDto.setTelephone("+7777777");
        requestDto.setUrl("url");
        requestDto.setNit("1234");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(requestDto);

        //when
        when(restaurantHandler.saveRestaurant(any(RestaurantRequestDto.class)))
                .thenReturn(responseDto);
        MockHttpServletRequestBuilder request = post("/square/restaurant/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.address").value("address"));
    }

    @Test
    void getRestaurantsWhitPaginationShouldReturnRestaurantPage() throws Exception {
        //given
        List<RestaurantPageDto> restaurantDto = new ArrayList<>();
        RestaurantPageDto requestDto = new RestaurantPageDto();
        restaurantDto.add(requestDto);
        Page<RestaurantPageDto> pagedResponse = new PageImpl(restaurantDto);

        //when
        when(restaurantHandler.getRestaurantsByPage(1,1,"ascending"))
                .thenReturn(pagedResponse);
        MockHttpServletRequestBuilder request = get("/square/restaurant/pagination")
                .param("page","1")
                .param("size", "1")
                .param("sort", "ascending");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }
}