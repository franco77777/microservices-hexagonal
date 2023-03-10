package com.pragma.square.domain.usecase;

import com.pragma.square.domain.models.CategoryModel;
import com.pragma.square.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void shouldCreateCategory() {
        //given
        CategoryModel expected = new CategoryModel();

        //when
        Mockito.when(categoryPersistencePort.createCategory(Mockito.any()))
                .thenReturn(expected);
        CategoryModel result = categoryUseCase.createCategory(expected);

        //them
        Mockito.verify(categoryPersistencePort, Mockito.times(1)).createCategory(expected);
        Assertions.assertEquals(expected, result);
    }
}