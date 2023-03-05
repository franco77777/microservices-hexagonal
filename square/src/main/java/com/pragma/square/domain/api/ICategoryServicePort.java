package com.pragma.square.domain.api;

import com.pragma.square.domain.models.CategoryModel;

public interface ICategoryServicePort {
    CategoryModel createCategory(CategoryModel categoryModel) ;
}
