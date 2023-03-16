package com.pragma.square.infrastructure.output.adapter;

import com.pragma.square.domain.models.CategoryModel;
import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.mapper.ICategoryEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {
    @Mock
    ICategoryRepository categoryRepository;
    @Mock
    ICategoryEntityMapper categoryEntityMapper;
    @InjectMocks
    CategoryJpaAdapter categoryJpaAdapter;

    @Test
    void createCategory() {
        //given
        Optional<CategoryEntity> category =  Optional.empty();
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryModel categoryModel = new CategoryModel();
        CategoryModel categoryModel2 = new CategoryModel();
        CategoryModel expected = new CategoryModel();
        CategoryModel myData = Mockito.mock(CategoryModel.class);

        //when
        when(categoryRepository.findByCategoryName(any()))
                .thenReturn(category);
        when(categoryEntityMapper.toEntity(any(CategoryModel.class)))
                .thenReturn(categoryEntity);
        when(categoryEntityMapper.toModel(any(CategoryEntity.class)))
                .thenReturn(expected);
        when(categoryRepository.save(any(CategoryEntity.class)))
                .thenReturn(categoryEntity);
        CategoryModel result = categoryJpaAdapter.createCategory(categoryModel);

        //then
        Assertions.assertEquals(expected, result);


    }
}