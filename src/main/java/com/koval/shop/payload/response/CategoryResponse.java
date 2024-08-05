package com.koval.shop.payload.response;

import java.util.Objects;
import java.util.UUID;

public class CategoryResponse {

    private UUID id;
    private String name;
    private byte[] logo;

    public CategoryResponse() {

    }

    private CategoryResponse(CategoryResponse.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.logo = builder.logo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryResponse that = (CategoryResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static class Builder {
        private UUID id;
        private String name;
        private byte[] logo;

        public Builder() {

        }

        public CategoryResponse.Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public CategoryResponse.Builder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryResponse.Builder logo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        public CategoryResponse build() {
            return new CategoryResponse(this);
        }
    }

    public static CategoryResponse.Builder builder() {
        return new CategoryResponse.Builder();
    }
}