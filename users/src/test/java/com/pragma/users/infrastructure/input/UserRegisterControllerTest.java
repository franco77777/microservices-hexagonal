package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.mapper.IUserRequestMapper;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import com.pragma.users.infrastructure.output.utils.SquareService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRegisterControllerTest {
    @Mock
    IUserHandler objectHandler;
    @Mock
     IUserRequestMapper objectRequestMapper;
    @Mock
     SquareService squareService;
    @InjectMocks
    UserRegisterController userRegisterController;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRegisterController).build();
    }

    @Test
    void saveClientShouldReturnToken() throws Exception {
        //given
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken("token");
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setDni("1234567");
        userRequestDto.setPassword("password");
        userRequestDto.setEmail("franco@gmail.com");
        userRequestDto.setFirstname("firstname");
        userRequestDto.setMobile("+1234567");
        userRequestDto.setLastname("lastname");


        //when
        when(objectHandler.emailExists("franco@gmail.com"))
                .thenReturn(true);
        when(objectHandler.saveUser(userRequestDto,AuthorityName.ROLE_CLIENT))
                .thenReturn(tokenResponseDto);
        MockHttpServletRequestBuilder request = post("/user/register/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"franco@gmail.com\", \"password\": \"password\", \"firstname\": \"firstname\"" +
                        ", \"lastname\": \"lastname\", \"mobile\": \"+1234567\", \"dni\": \"1234567\"}");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("token"))
        ;
    }

    @Test
    void saveAdmin() {
    }

    @Test
    void saveOwner() {
    }

    @Test
    void saveEmployer() {
    }
}