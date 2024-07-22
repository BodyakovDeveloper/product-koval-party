package com.koval.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koval.shop.model.CategoryEntity;
import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.repository.CategoryRepository;
import com.koval.shop.repository.ProductRepository;
import com.koval.shop.service.api.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.koval.shop.testdata.factory.category.CategoryFactory.buildCategoryToSave;
import static com.koval.shop.testdata.factory.product.ProductFactory.buildProductToSave;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIntegrationTest extends ControllerTestBase {

    public static final String BASE_PRODUCT_PATH = "/api/v1/product";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    public void removeAll() {
        productRepository.deleteAll();
    }

    @Test
    @Transactional
    public void getProductById_shouldReturnUnauthorized_whenAuthenticationIsNotPresent() throws Exception {
        // When
        mockMvc.perform(get(BASE_PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER", "EDITOR"})
    @Transactional
    public void getProductById_shouldReturnProduct_whenAuthenticationIsValidAndParametersAreValid() throws Exception {
        // Given
        ProductEntity productEntity = createProduct();

        // When
        MvcResult result = mockMvc.perform(get("/api/v1/product/" + productEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        ProductEntity resultEntity = objectMapper.readValue(contentAsString, ProductEntity.class);

        assertEquals(productEntity.getId(), resultEntity.getId());
        assertEquals(productEntity.getName(), resultEntity.getName());
        assertEquals(productEntity.getCategory(), resultEntity.getCategory());
    }

    @Test
    public void updateProductName_shouldReturnNonAuthorized_whenAuthenticationIsNotPresent() throws Exception {
        // Given

        UpdateProductRequest updateRequest = new UpdateProductRequest();
        updateRequest.setId(UUID.randomUUID());
        updateRequest.setName("New Product Name");

        // When
        mockMvc.perform(patch(BASE_PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"EDITOR"})
    @Transactional
    public void updateProductName_shouldUpdateName_whenAuthenticationIsValidAndParametersAreValid() throws Exception {
        // Given
        ProductEntity productEntity = createProduct();

        UpdateProductRequest updateRequest = new UpdateProductRequest();
        updateRequest.setId(productEntity.getId());
        String newProductName = "New Product Name";
        updateRequest.setName(newProductName);

        // When
        mockMvc.perform(put(BASE_PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());

        // Then
        ProductEntity updatedProductEntity = productRepository.findById(productEntity.getId()).orElse(null);
        assertNotNull(updatedProductEntity);
        assertEquals(newProductName, updatedProductEntity.getName());
    }

    private ProductEntity createProduct() {
        CategoryEntity categoryEntity = buildCategoryToSave();
        ProductEntity productEntity = buildProductToSave(categoryEntity);

        categoryRepository.save(categoryEntity);
        productRepository.save(productEntity);

        return productEntity;
    }
}
