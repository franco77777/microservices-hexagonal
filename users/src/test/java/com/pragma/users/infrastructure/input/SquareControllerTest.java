package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.response.AuthorityNameResponseDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.repository.IUserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = SquareController.class)
//@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SquareControllerTest {
    @Mock
    IUserHandler userHandler;
    @Mock
    IUserRepository userRepository;
    @Autowired
    MockMvc mockMvc;
    @InjectMocks
    SquareController squareController;
    @BeforeEach
    void setUp() {
     mockMvc = MockMvcBuilders.standaloneSetup(squareController).build();
    }

    @Test
    void getUserPhoneShouldReturnString() throws Exception {
        //given
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(1L);
        userResponse.setMobile("123456789");

        //when
        when(userHandler.getUser(1L))
                .thenReturn(userResponse);
        MockHttpServletRequestBuilder request = get("/user/square/1");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("123456789"));
    }

    @Test
    void getRoleShouldReturnBoolean() throws Exception {
        //given
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(1L);
        userResponse.setRoles(AuthorityNameResponseDto.ROLE_OWNER);

        //when
        when(userHandler.getUser(1L))
                .thenReturn(userResponse);
        MockHttpServletRequestBuilder request = get("/user/square/role/1");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }
}