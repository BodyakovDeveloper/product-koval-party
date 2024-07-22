package com.koval.shop.service.impl;

import com.koval.shop.exception.CategoryNotFoundException;
import com.koval.shop.exception.ImageProcessingException;
import com.koval.shop.mapper.CategoryMapper;
import com.koval.shop.model.CategoryEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.payload.response.CategoryWithProductsResponse;
import com.koval.shop.repository.CategoryRepository;
import com.koval.shop.service.api.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Modifying
    @Transactional
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        // validator, but nothing to validate
        log.debug("Start createCategory logo service with categoryRequest: {}", createCategoryRequest);

        CategoryEntity category = categoryMapper.toCategory(createCategoryRequest);
        CategoryEntity savedCategory = categoryRepository.save(category);

        CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(savedCategory);
        log.debug("Start createCategory logo service with categoryResponse: {}", categoryResponse);
        return categoryResponse;
    }

    @Override
    public CategoryWithProductsResponse getByIdWithProducts(UUID categoryId) {
        log.debug("Start CategoryServiceImpl getById service with categoryId: {}", categoryId);

        CategoryEntity category = categoryRepository.findByIdWithProducts(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id: %s not found", categoryId)));

        CategoryWithProductsResponse categoryResponse = categoryMapper.toCategoryWithProductsResponse(category);

        log.debug("End CategoryServiceImpl getById service with categoryId: {}", categoryId);
        return categoryResponse;
    }

    @Override
    @Modifying
    @Transactional
    public CategoryResponse updateLogo(UUID categoryId, MultipartFile logo) {
        log.debug("Start updateLogo service with categoryId={} and logo={}", categoryId, logo);

        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id: %s not found", categoryId)));

        byte[] imageInBytes;
        try {
            imageInBytes = logo.getBytes();
        } catch (IOException e) {
            log.error("Error processing image", e);
            throw new ImageProcessingException("Error processing image bytes", e);
        }
        categoryEntity.setLogo(imageInBytes);

        return categoryMapper.toCategoryResponse(categoryEntity);
    }

    @Override
    public void delete(UUID categoryId) {
        log.debug("Start CategoryServiceImpl delete category with id: {}", categoryId);

        if (Boolean.FALSE.equals(categoryRepository.existsById(categoryId))) {
            throw new CategoryNotFoundException(
                    String.format("Category with id: %s not found", categoryId));
        }
        categoryRepository.deleteById(categoryId);

        log.debug("End CategoryServiceImpl delete category with id: {}", categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoryResponse> getPaginatedCategories(Pageable pageRequest) {
        return categoryRepository.findAll(pageRequest)
                .map(categoryMapper::toCategoryResponse);
    }
}
