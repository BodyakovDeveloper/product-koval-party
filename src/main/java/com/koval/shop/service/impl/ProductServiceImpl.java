package com.koval.shop.service.impl;

import com.koval.shop.exception.CategoryNotFoundException;
import com.koval.shop.exception.ImageProcessingException;
import com.koval.shop.exception.ProductNotFoundException;
import com.koval.shop.mapper.ProductMapper;
import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.ProductSearchRequest;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.payload.response.ProductResponse;
import com.koval.shop.repository.ProductRepository;
import com.koval.shop.repository.specification.ProductSearchSpecification;
import com.koval.shop.service.api.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, com.koval.shop.mapper.ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(UUID productId) {
        log.debug("Start getting products from productId={}", productId);

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", productId)));

        return productMapper.toProductResponse(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getPaginatedProductsWithLogos(Pageable pageable) {
        log.debug("Start getting products from pageable={}", pageable);

        List<ProductEntity> productEntities = productRepository.findProductsWithCategory(pageable);
        List<ProductResponse> ProductResponses = productEntities.stream()
                .map(productMapper::toProductResponse)
                .toList();

        return new PageImpl<>(ProductResponses, pageable, productEntities.size());
    }

    @Override
    @Modifying
    @Transactional
    public ProductResponse updateProduct(UpdateProductRequest updateProductRequest) {
        log.debug("Start updating Product with dtp={}", updateProductRequest);

        UUID productId = updateProductRequest.getId();
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", productId)));
        productEntity.setName(updateProductRequest.getName());

        if (updateProductRequest.getName() != null) {
            productEntity.setName(updateProductRequest.getName());
        }

        if (updateProductRequest.getLogo() != null) {
            byte[] imageInBytes;
            try {
                imageInBytes = updateProductRequest.getLogo().getBytes();
            } catch (IOException e) {
                log.error("Error processing image", e);
                throw new ImageProcessingException("Error processing image bytes", e);
            }
            productEntity.setLogo(imageInBytes);
        }

        return productMapper.toProductResponse(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> searchProducts(ProductSearchRequest productSearchRequest) {
        log.debug("Start searching products from nameContaining={}", productSearchRequest);

        Specification<ProductEntity> productSpecification = ProductSearchSpecification.byNameAndCategories(productSearchRequest);
        List<ProductEntity> products = productRepository.findAll(productSpecification);

        return productMapper.toProductResponse(products);
    }

    @Override
    public void delete(UUID categoryId) {

        if (Boolean.FALSE.equals(productRepository.existsById(categoryId))) {
            throw new CategoryNotFoundException(
                    String.format("Category with id: %s not found", categoryId));
        }
        productRepository.deleteById(categoryId);
    }
}
