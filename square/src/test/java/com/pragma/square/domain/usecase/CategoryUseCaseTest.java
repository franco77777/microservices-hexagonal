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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        when(categoryPersistencePort.createCategory(any()))
                .thenReturn(expected);
        CategoryModel result = categoryUseCase.createCategory(expected);

        //them
        verify(categoryPersistencePort, times(1)).createCategory(expected);
        assertEquals(expected, result);
    }
}