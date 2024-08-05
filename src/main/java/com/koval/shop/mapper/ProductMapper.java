package com.koval.shop.mapper;

import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "category", source = "category")
    ProductResponse toProductResponse(ProductEntity ProductEntity);

    List<ProductResponse> toProductResponse(List<ProductEntity> ProductEntity);

    List<ProductResponse> toProductResponseList(List<ProductEntity> productEntities);
}
