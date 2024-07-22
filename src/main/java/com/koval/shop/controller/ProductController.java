package com.koval.shop.controller;

import com.koval.shop.payload.request.ProductSearchRequest;
import com.koval.shop.payload.request.UpdateProductRequest;
import com.koval.shop.payload.response.ProductResponse;
import com.koval.shop.service.api.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return a response entity containing the product response
     */
    @GetMapping("{productId}")
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID productId) {
        log.debug("ProductController getProducts starts with productId={}", productId);

        ProductResponse product = productService.getById(productId);

        log.debug("ProductController getProducts ends, returning product={}", product);
        return ResponseEntity.ok().body(product);
    }

    /**
     * Retrieves paginated products. Additional info for pagination also included.
     *
     * @param pageRequest the page request
     * @return a page of product responses
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public Page<ProductResponse> getPaginatedProducts(Pageable pageRequest) {
        log.debug("ProductController getProducts starts with pageRequest={}", pageRequest);

        Page<ProductResponse> products = productService.getPaginatedProductsWithLogos(pageRequest);

        log.debug("ProductController getProducts ends, returning products={}", products);
        return products;
    }

    /**
     * Searches for products byr productName and categoryNames.
     *
     * @param productSearchRequest the product search request
     * @return a response entity containing the list of found products
     */
    @GetMapping("search")
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public ResponseEntity<List<ProductResponse>> searchProducts(ProductSearchRequest productSearchRequest) {
        log.debug("ProductController searchProducts starts with nameContaining={}", productSearchRequest);

        List<ProductResponse> ProductResponses = productService.searchProducts(productSearchRequest);

        log.debug("ProductController searchProducts ends, returning ProductResponses={}", ProductResponses);
        return ResponseEntity.ok(ProductResponses);
    }

    /**
     * Updates a product's name and logo.
     *
     * @param updateProductRequest the update product request
     * @return a response entity containing the updated product response
     */
    @PutMapping
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        log.debug("ProductController updateCategory starts with updateProductNameRequest={}", updateProductRequest);

        ProductResponse response = productService.updateProduct(updateProductRequest);

        log.debug("ProductController updateCategory ends, returning response={}", response);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product
     * @return a response entity indicating the deletion result
     */
    @DeleteMapping
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<?> deleteProduct(@RequestParam("productId") UUID productId) {
        log.debug("ProductController deleteCategory starts with productId={}", productId);

        productService.delete(productId);

        log.debug("ProductController deleteCategory ends with productId={}", productId);
        return ResponseEntity.accepted().build();
    }
}
