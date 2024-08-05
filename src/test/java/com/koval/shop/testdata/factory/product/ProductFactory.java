package com.koval.shop.testdata.factory.product;

import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.ProductSearchRequest;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.payload.response.ProductResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.util.UUID;

public class ProductFactory {

    public static ProductEntity createProductEntity() {
        return ProductEntity.builder()
                .id(UUID.randomUUID())
                .name("Product Name")
                .logo("logo".getBytes())
                .build();
    }

    public static ProductResponse createProductResponse() {
        return ProductResponse.builder()
                .id(UUID.randomUUID())
                .name("Product Name")
                .build();
    }

    public static UpdateProductRequest createUpdateProductRequest() {
        return UpdateProductRequest.builder()
                .id(UUID.randomUUID())
                .name("Updated Product Name")
                .logo(new MockMultipartFile("logo", "logo".getBytes()))
                .build();
    }

    public static ProductSearchRequest createProductSearchRequest() {
        return ProductSearchRequest.builder()
                .productName("Product Name")
                .build();
    }
}