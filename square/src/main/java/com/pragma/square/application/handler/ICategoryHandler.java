package com.pragma.square.application.handler;

import com.pragma.square.application.request.CategoryRequestDto;
import com.pragma.square.application.response.CategoryResponseDto;

public interface ICategoryHandler {
    CategoryResponseDto createCategory(CategoryRequestDto name);
}
