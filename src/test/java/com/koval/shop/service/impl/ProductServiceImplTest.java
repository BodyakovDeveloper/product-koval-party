package com.koval.shop.service.impl;

import com.koval.shop.exception.ProductNotFoundException;
import com.koval.shop.mapper.ProductMapper;
import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.ProductSearchRequest;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.payload.response.ProductResponse;
import com.koval.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.koval.shop.testdata.factory.product.ProductFactory.createProductEntity;
import static com.koval.shop.testdata.factory.product.ProductFactory.createProductResponse;
import static com.koval.shop.testdata.factory.product.ProductFactory.createProductSearchRequest;
import static com.koval.shop.testdata.factory.product.ProductFactory.createUpdateProductRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService; // Assuming your service implementation is named ProductServiceImpl

    @Test
    void getById_shouldReturnProductResponse_whenProductExists() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = createProductEntity();
        ProductResponse productResponse = createProductResponse();

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(productMapper.toProductResponse(productEntity)).thenReturn(productResponse);

        ProductResponse response = productService.getById(productId);

        verify(productRepository).findById(productId);
        verify(productMapper).toProductResponse(productEntity);
        assertEquals(productResponse, response);
    }

    @Test
    void getById_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getById(productId));

        verify(productRepository).findById(productId);
    }

    @Test
    void delete_shouldDeleteProduct_whenProductExists() {
        UUID productId = UUID.randomUUID();
        when(productRepository.existsById(productId)).thenReturn(true);

        productService.delete(productId);

        verify(productRepository).existsById(productId);
        verify(productRepository).deleteById(productId);
    }

    @Test
    void delete_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.delete(productId));

        verify(productRepository).existsById(productId);
    }
}