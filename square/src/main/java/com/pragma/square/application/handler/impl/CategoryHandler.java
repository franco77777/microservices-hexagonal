package com.pragma.square.application.handler.impl;

import com.pragma.square.application.handler.ICategoryHandler;
import com.pragma.square.application.mapper.ICategoryRequestMapper;
import com.pragma.square.application.mapper.ICategoryResponseMapper;
import com.pragma.square.application.request.CategoryRequestDto;
import com.pragma.square.application.response.CategoryResponseDto;
import com.pragma.square.domain.api.ICategoryServicePort;
import com.pragma.square.domain.models.CategoryModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryHandler implements ICategoryHandler {
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto name) {
        CategoryModel categoryModel = categoryRequestMapper.toCategoryModel(name);
        return categoryResponseMapper.toCategoryResponseDto(categoryServicePort.createCategory(categoryModel));
    }
}
