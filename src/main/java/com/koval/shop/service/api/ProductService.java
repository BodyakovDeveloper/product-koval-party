package com.koval.shop.service.api;

import com.koval.shop.payload.request.ProductSearchRequest;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.payload.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse getById(UUID id);

    Page<ProductResponse> getPaginatedProductsWithLogos(Pageable pageable);

    ProductResponse updateProduct(UpdateProductRequest updateProductRequest);

    List<ProductResponse> searchProducts(ProductSearchRequest productSearchRequest);

    void delete(UUID categoryId);
}
