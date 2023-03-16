package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IEmployeeHandler;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @Mock
    IEmployeeHandler employeeHandler;
    @InjectMocks
    EmployeeController employeeController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void createEmployeeShouldReturnBoolean() throws Exception {
        //given
        Long ownerId = 1L;
        Long restaurantId = 1L;
        Long employeeId = 1L;

        //when
        MockHttpServletRequestBuilder request = post("/square/users/1/1/1")
                .contentType(MediaType.APPLICATION_JSON);
        employeeController.createEmployee(ownerId, restaurantId, employeeId);

        //then
        verify(employeeHandler, Mockito.times(1)).createEmployee(ownerId, restaurantId, employeeId);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("true"));
    }
}
