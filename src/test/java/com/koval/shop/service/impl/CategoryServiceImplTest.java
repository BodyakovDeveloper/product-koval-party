package com.koval.shop.service.impl;

import com.koval.shop.exception.CategoryNotFoundException;
import com.koval.shop.mapper.CategoryMapper;
import com.koval.shop.model.CategoryEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;
import com.koval.shop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.koval.shop.testdata.factory.category.CategoryFactory.createCategoryEntity;
import static com.koval.shop.testdata.factory.category.CategoryFactory.createCategoryRequest;
import static com.koval.shop.testdata.factory.category.CategoryFactory.createCategoryResponse;
import static com.koval.shop.testdata.factory.category.CategoryFactory.createCategoryWithProductsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    void createCategory_shouldReturnCategoryResponse_whenValidRequest() {

        CategoryEntity categoryEntity = createCategoryEntity();
        CategoryResponse categoryResponse = createCategoryResponse();
        CreateCategoryRequest createCategoryRequest = createCategoryRequest();

        when(categoryMapper.toCategory(any(CreateCategoryRequest.class))).thenReturn(categoryEntity);
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(categoryEntity);
        when(categoryMapper.toCategoryResponse(any(CategoryEntity.class))).thenReturn(categoryResponse);

        CategoryResponse response = categoryService.create(createCategoryRequest);

        verify(categoryMapper).toCategory(createCategoryRequest);
        verify(categoryRepository).save(categoryEntity);
        verify(categoryMapper).toCategoryResponse(categoryEntity);
        assertEquals(categoryResponse, response);
    }

    @Test
    void getByIdWithProducts_shouldReturnCategoryWithProductsResponse_whenCategoryExists() {
        CategoryEntity categoryEntity = createCategoryEntity();
        CategoryWithProductsResponse categoryWithProductsResponse = createCategoryWithProductsResponse();
        UUID categoryId = UUID.randomUUID();
        when(categoryRepository.findByIdWithProducts(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(categoryMapper.toCategoryWithProductsResponse(any(CategoryEntity.class))).thenReturn(categoryWithProductsResponse);

        CategoryWithProductsResponse response = categoryService.getByIdWithProducts(categoryId);

        verify(categoryRepository).findByIdWithProducts(categoryId);
        verify(categoryMapper).toCategoryWithProductsResponse(categoryEntity);
        assertEquals(categoryWithProductsResponse, response);
    }

    @Test
    void getByIdWithProducts_shouldThrowCategoryNotFoundException_whenCategoryDoesNotExist() {
        UUID categoryId = UUID.randomUUID();
        when(categoryRepository.findByIdWithProducts(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getByIdWithProducts(categoryId));

        verify(categoryRepository).findByIdWithProducts(categoryId);
    }

    @Test
    void deleteCategory_shouldDeleteCategory_whenCategoryExists() {
        UUID categoryId = UUID.randomUUID();
        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.delete(categoryId);

        verify(categoryRepository).existsById(categoryId);
        verify(categoryRepository).deleteById(categoryId);
    }

    @Test
    void deleteCategory_shouldThrowCategoryNotFoundException_whenCategoryDoesNotExist() {
        UUID categoryId = UUID.randomUUID();
        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.delete(categoryId));

        verify(categoryRepository).existsById(categoryId);
    }
}