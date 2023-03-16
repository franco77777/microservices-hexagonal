package com.pragma.square.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.square.application.handler.IPlateHandler;
import com.pragma.square.application.request.PlateRequestDto;
import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.application.response.PlateResponseDto;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PlateControllerTest {
    @Mock
    IPlateHandler plateHandler;

    @InjectMocks
    PlateController plateController;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(plateController).build();
    }

    @Test
    void platesPageShouldReturnPageOfPlateResponseDto() throws Exception {
        //given
        List<PlateResponseDto> plates = new ArrayList<>();
        PlateResponseDto plateResponseDto = new PlateResponseDto();
        plateResponseDto.setId(1L);
        plates.add(plateResponseDto);
        Page<PlateResponseDto> pagedResponse = new PageImpl(plates);

        //when
        MockHttpServletRequestBuilder request = get("/square/plate/pagination/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page","1")
                .param("size","1")
                .param("sort", "ascending")
                .param("property", "id");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createShoudlReturnPlateResonseDto() throws Exception {
        //given
        PlateRequestDto plateRequestDto = new PlateRequestDto();
        plateRequestDto.setDescription("description");
        plateRequestDto.setName("name");
        plateRequestDto.setImageUrl("imageUrl");
        plateRequestDto.setPrice("12345");
        PlateResponseDto plateResponseDto = new PlateResponseDto();
        plateResponseDto.setDescription("description");
        plateResponseDto.setName("name");
        plateResponseDto.setImageUrl("imageUrl");
        plateResponseDto.setPrice("12345");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(plateRequestDto);

        //when
                when(plateHandler.savePlate(any(PlateRequestDto.class), anyLong(),anyLong()))
                .thenReturn(plateResponseDto);
        MockHttpServletRequestBuilder request = post("/square/plate/create/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.imageUrl").value("imageUrl"))
                .andExpect(jsonPath("$.price").value("12345"));

    }

    @Test
    void updateShouldReturnPlateResponseDto() throws Exception {
        //given
        PlateUpdateRequestDto plateUpdateRequestDto = new PlateUpdateRequestDto();
        plateUpdateRequestDto.setDescription("description");
        plateUpdateRequestDto.setPrice("12345");
        PlateResponseDto plateResponseDto = new PlateResponseDto();
        plateResponseDto.setDescription("description");
        plateResponseDto.setPrice("12345");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(plateUpdateRequestDto);

        //when
        when(plateHandler.updatePlate(any(PlateUpdateRequestDto.class),anyLong()))
                .thenReturn(plateResponseDto);
        MockHttpServletRequestBuilder request = put("/square/plate/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.price").value("12345"));

    }

    @Test
    void deactivatePlateShouldReturnPlateResponseDto() throws Exception {
        //given
        PlateResponseDto plateResponseDto = new PlateResponseDto();
        plateResponseDto.setId(1L);

        //when
        when(plateHandler.deactivatePlate(1L))
                .thenReturn(plateResponseDto);
        MockHttpServletRequestBuilder request = put("/square/plate/deactivate/1")
                .contentType(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
}