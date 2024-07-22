
package com.koval.shop.controller;

import com.koval.shop.annotation.ValidLogo;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;
import com.koval.shop.service.api.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EDITOR')")
    public ResponseEntity<CategoryResponse> create(@RequestBody CreateCategoryRequest createCategoryRequest) {
        log.debug("CategoryController createCategory starts with pageRequest={}", createCategoryRequest);

        CategoryResponse category = categoryService.create(createCategoryRequest);

        log.debug("categoryController createCategory ends, returning category={}", category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("{categoryId}")
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public ResponseEntity<CategoryWithProductsResponse> getById(@PathVariable UUID categoryId) {
        log.debug("CategoryController getCategory starts with categoryId={}", categoryId);

        CategoryWithProductsResponse category = categoryService.getByIdWithProducts(categoryId);

        log.debug("categoryController getCategory ends, returning categories={}", category);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public Page<CategoryResponse> getPaginatedCategories(Pageable pageRequest) {
        log.debug("CategoryController getCategories starts with pageRequest={}", pageRequest);

        Page<CategoryResponse> categories = categoryService.getPaginatedCategories(pageRequest);

        log.debug("categoryController getCategories ends, returning categories={}", categories);
        return categories;
    }

    @PatchMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<CategoryResponse> update(@RequestParam("categoryId") UUID categoryId,
                                                           @ValidLogo @RequestParam("logo") MultipartFile logo) {
        log.debug("CategoryController updateCategory starts with categoryId={} and logo={}", categoryId, logo.getOriginalFilename());

        CategoryResponse categoryResponse = categoryService.updateLogo(categoryId, logo);

        log.debug("CategoryController updateCategory ends, returning categoryResponse={}", categoryResponse);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<?> delete(@RequestParam("categoryId") UUID categoryId) {
        log.debug("CategoryController deleteCategory starts with categoryId={}", categoryId);

        categoryService.delete(categoryId);

        log.debug("CategoryController deleteCategory ends with categoryId={}", categoryId);
        return ResponseEntity.accepted().build();
    }
}