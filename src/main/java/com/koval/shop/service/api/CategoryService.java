package com.koval.shop.service.api;

import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    CategoryWithProductsResponse getCategoryByIdWithProducts(UUID categoryId);

    Page<CategoryResponse> getPaginatedCategories(Pageable pageRequest);

    CategoryResponse updateCategoryLogo(UUID categoryId, MultipartFile logo);

    void deleteCategory(UUID categoryId);
}
