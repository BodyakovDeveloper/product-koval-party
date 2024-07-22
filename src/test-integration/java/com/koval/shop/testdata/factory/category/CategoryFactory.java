package com.koval.shop.testdata.factory.category;

import com.koval.shop.model.CategoryEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;

public class CategoryFactory {

    public static CategoryEntity buildCategoryToSave() {
        return CategoryEntity.builder()
                .name("Category name")
                .logo("logo".getBytes())
                .build();
    }


    public static CreateCategoryRequest buildCreateCategoryRequest() {
        return CreateCategoryRequest.builder()
                .name("category to save")
                .logo("logo".getBytes())
                .build();
    }
}
