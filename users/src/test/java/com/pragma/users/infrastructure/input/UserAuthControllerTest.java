package com.pragma.users.infrastructure.input;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.request.AuthenticateRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.infrastructure.output.mapper.IUserEntityMapper;
import com.pragma.users.infrastructure.security.JwtService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)

class UserAuthControllerTest {
    @Mock
     IUserHandler userHandler;
    @Mock
     JwtService jwtService;
    @Mock
     IUserEntityMapper userEntityMapper;

    @InjectMocks
    UserAuthController userAuthController;
    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    ObjectMapper objectMapper;





    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userAuthController).build();
    }

    @Test
    void loginShouldReturnToken() throws Exception {
        //given
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken("token");
        AuthenticateRequestDto authenticateRequestDto = new AuthenticateRequestDto();
        authenticateRequestDto.setPassword("password");
        authenticateRequestDto.setEmail("franco@gmail.com");
        Map<String,Object> body = new HashMap<>();
        body.put("password","123");
        body.put("email","franco@gmail.com");
        ObjectMapper mapper =  new ObjectMapper();
        String requestJson = mapper.writeValueAsString(authenticateRequestDto);

        //when
        when(userHandler.userLogin(authenticateRequestDto.getEmail(),authenticateRequestDto.getPassword()))
                .thenReturn(tokenResponseDto);
        MockHttpServletRequestBuilder request = post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                //.content(objectMapper.writeValueAsString(body));
                .content(requestJson);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("token"))
                ;






    }

    @Test
    void validateUserShoudlReturnToken() throws Exception {
        //given
        String token = "token";
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(token);

        //when
        when(userHandler.validateToken(token))
                .thenReturn(tokenResponseDto);
        MockHttpServletRequestBuilder request = post("/user/validate")
                .accept(MediaType.APPLICATION_JSON)
                .param("token",token);

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token));
    }
}