package com.koval.shop.testdata.factory.category;

import com.koval.shop.model.CategoryEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;

import java.util.List;
import java.util.UUID;

public class CategoryFactory {

    public static CreateCategoryRequest createCategoryRequest() {
        return CreateCategoryRequest.builder()
                .name("Category Name")
                .logo(new byte[5])
                .build();
    }

    public static CategoryEntity createCategoryEntity() {
        return CategoryEntity.builder()
                .id(UUID.randomUUID())
                .name("Category Name")
                .logo("logo".getBytes())
                .build();
    }

    public static CategoryResponse createCategoryResponse() {
        return CategoryResponse.builder()
                .id(UUID.randomUUID())
                .name("Category Name")
                .logo("logo".getBytes())
                .build();
    }

    public static CategoryWithProductsResponse createCategoryWithProductsResponse() {
        return CategoryWithProductsResponse.builder()
                .id(UUID.randomUUID())
                .name("Category Name")
                .logo("logo".getBytes())
                .products(List.of())
                .build();
    }
}
