package com.pragma.square.domain.usecase;

import com.pragma.square.domain.api.ICategoryServicePort;
import com.pragma.square.domain.models.CategoryModel;
import com.pragma.square.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) {
        return categoryPersistencePort.createCategory(categoryModel);
    }
}
