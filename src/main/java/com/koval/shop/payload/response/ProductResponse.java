package com.koval.shop.payload.response;

import com.koval.shop.payload.dto.CategoryDto;

import java.util.Objects;
import java.util.UUID;

public class ProductResponse {

    private UUID id;
    private String name;
    private CategoryDto category;

    public ProductResponse() {

    }

    public ProductResponse(UUID id, String name, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.category = category;
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductResponse that = (ProductResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }


    public static class Builder {

        private UUID id;
        private String name;
        private CategoryDto category;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(CategoryDto category) {
            this.category = category;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(id, name, category);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
