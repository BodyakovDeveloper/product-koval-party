package com.koval.shop.payload.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CategoryWithProductsResponse {

    private UUID id;

    private String name;

    private byte[] logo;

    private List<ProductResponse> products = new ArrayList<>();

    public CategoryWithProductsResponse() {

    }

    private CategoryWithProductsResponse(CategoryWithProductsResponse.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.logo = builder.logo;
        this.products = builder.products;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryWithProductsResponse categoryEntity = (CategoryWithProductsResponse) o;
        return Objects.equals(id, categoryEntity.id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> productResponses) {
        this.products = productResponses;
    }

    public static class Builder {

        private UUID id;
        private String name;
        private byte[] logo;
        private List<ProductResponse> products = new ArrayList<>();

        public Builder() {

        }

        public CategoryWithProductsResponse.Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public CategoryWithProductsResponse.Builder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryWithProductsResponse.Builder logo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        public CategoryWithProductsResponse.Builder products(List<ProductResponse> productResponse) {
            this.products.addAll(productResponse);
            return this;
        }

        public CategoryWithProductsResponse build() {
            return new CategoryWithProductsResponse(this);
        }
    }

    public static CategoryWithProductsResponse.Builder builder() {
        return new CategoryWithProductsResponse.Builder();
    }

}
