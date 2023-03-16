package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.ICategoryHandler;
import com.pragma.square.application.request.CategoryRequestDto;
import com.pragma.square.application.response.CategoryResponseDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    ICategoryHandler categoryHandler;
    @InjectMocks
    CategoryController categoryController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }


    @Test
    void createShouldReturnCategoryResponseDto() throws Exception {
        //given
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setCategoryName("categoryName");
        categoryRequestDto.setDescription("description");
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryName("categoryName");
        categoryResponseDto.setDescription("description");

        //when
        when(categoryHandler.createCategory(categoryRequestDto))
                .thenReturn(categoryResponseDto);
        MockHttpServletRequestBuilder request = post("/square/category")
                .contentType("application/json")
                .content("{\"categoryName\": \"categoryName\", \"description\": \"description\"}");

        //then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryName").value("categoryName"))
                .andExpect(jsonPath("$.description").value("description"));
    }
}