package com.koval.shop.service.impl;

import com.koval.shop.exception.ProductNotFoundException;
import com.koval.shop.mapper.ProductMapper;
import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.response.ProductResponse;
import com.koval.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.koval.shop.testdata.factory.product.ProductFactory.createProductEntity;
import static com.koval.shop.testdata.factory.product.ProductFactory.createProductResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    void getProductById_shouldReturnProductResponse_whenProductExists() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = createProductEntity();
        ProductResponse productResponse = createProductResponse();

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(productMapper.toProductResponse(productEntity)).thenReturn(productResponse);

        ProductResponse response = productService.getProductById(productId);

        verify(productRepository).findById(productId);
        verify(productMapper).toProductResponse(productEntity);
        assertEquals(productResponse, response);
    }

    @Test
    void getProductById_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));

        verify(productRepository).findById(productId);
    }

    @Test
    void delete_shouldDeleteProductProduct_whenProductExists() {
        UUID productId = UUID.randomUUID();
        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProduct(productId);

        verify(productRepository).existsById(productId);
        verify(productRepository).deleteById(productId);
    }

    @Test
    void delete_Product_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productRepository.existsById(productId)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(productId));

        verify(productRepository).existsById(productId);
    }
}