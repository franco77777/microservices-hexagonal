package com.pragma.square.domain.spi;

import com.pragma.square.domain.models.CategoryModel;

public interface ICategoryPersistencePort {


    CategoryModel createCategory(CategoryModel categoryModel);
}
