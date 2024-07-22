package com.koval.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koval.shop.model.CategoryEntity;
import com.koval.shop.payload.request.CreateCategoryRequest;
import com.koval.shop.payload.response.CategoryResponse;
import com.koval.shop.repository.CategoryRepository;
import com.koval.shop.service.api.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

import static com.koval.shop.testdata.factory.category.CategoryFactory.buildCategoryToSave;
import static com.koval.shop.testdata.factory.category.CategoryFactory.buildCreateCategoryRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerIntegrationTest extends ControllerTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void getCategoryById_shouldReturnUnauthorized_whenAuthenticationIsNotPresent() throws Exception {
        // When
        mockMvc.perform(get("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productName", String.valueOf(UUID.randomUUID()))
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER", "EDITOR"})
    @Transactional
    public void getCategoryById_shouldReturnCategory_whenAuthenticationIsPresentAndParamIsValid() throws Exception {

        CategoryEntity categoryEntity = createCategory();
        // When
        MvcResult result = mockMvc.perform(get("/api/v1/category/" + categoryEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        CategoryResponse resultResponse = objectMapper.readValue(contentAsString, CategoryResponse.class);

        assertEquals(categoryEntity.getId(), resultResponse.getId());
        assertEquals(categoryEntity.getName(), resultResponse.getName());
        assertEquals(Arrays.toString(categoryEntity.getLogo()), Arrays.toString(resultResponse.getLogo()));
    }

    @Test
    @Transactional
    public void createCategory_shouldReturnUnauthorized_whenAuthenticationIsNotPresent() throws Exception {
        CreateCategoryRequest createCategoryRequest = buildCreateCategoryRequest();
        String requestJson = objectMapper.writeValueAsString(createCategoryRequest);

        // When
        mockMvc.perform(post("/api/v1/category")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER", "EDITOR"})
    @Transactional
    public void createCategory_shouldReturnCategory_whenAuthenticationIsPresentAndParamIsValid() throws Exception {
        CreateCategoryRequest createCategoryRequest = buildCreateCategoryRequest();
        String requestJson = objectMapper.writeValueAsString(createCategoryRequest);

        // When
        MvcResult result = mockMvc.perform(post("/api/v1/category")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()).andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        CategoryResponse categoryResponse = objectMapper.readValue(contentAsString, CategoryResponse.class);

        assertEquals(createCategoryRequest.getName(), categoryResponse.getName());
        assertEquals(Arrays.toString(createCategoryRequest.getLogo()), Arrays.toString(categoryResponse.getLogo()));
    }

    private CategoryEntity createCategory() {
        CategoryEntity category = buildCategoryToSave();
        return categoryRepository.save(category);
    }
}
