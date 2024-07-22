package com.koval.shop.testdata.factory.product;

import com.koval.shop.model.CategoryEntity;
import com.koval.shop.model.ProductEntity;

public class ProductFactory {

    public static ProductEntity buildProductToSave(CategoryEntity categoryEntity) {
        return ProductEntity.builder()
                .name("Category name")
                .category(categoryEntity)
                .build();
    }
}
