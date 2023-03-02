package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.CategoryModel;
import com.pragma.square.domain.spi.ICategoryPersistencePort;
import com.pragma.square.infrastructure.exception.InfrastructureException;
import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.mapper.ICategoryEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) {
        if(categoryRepository.findByCategoryName(categoryModel.getCategoryName()).isPresent()) throw new InfrastructureException("Category name already exists", HttpStatus.BAD_REQUEST);
        CategoryEntity result = categoryEntityMapper.toEntity(categoryModel);
        return categoryEntityMapper.toModel(categoryRepository.save(result));
    }
}
