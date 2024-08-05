package com.koval.shop.mapper;

import com.koval.shop.model.CategoryEntity;
import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;
import com.koval.shop.payload.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(CategoryEntity categoryEntity);

    ProductResponse toProductResponse(ProductEntity productEntity);

    CategoryWithProductsResponse toCategoryWithProductsResponse(CategoryEntity categoryEntity);

    CategoryEntity toCategory(CreateCategoryRequest createCategoryRequest);
}
