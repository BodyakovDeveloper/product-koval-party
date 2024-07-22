package com.koval.shop.payload.request;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class CreateCategoryRequest {

    private UUID id;

    private String name;

    private byte[] logo;

    public CreateCategoryRequest() {

    }

    private CreateCategoryRequest(CreateCategoryRequest.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.logo = builder.logo;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCategoryRequest categoryEntity = (CreateCategoryRequest) o;
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

    public static class Builder {

        private UUID id;
        private String name;
        private byte[] logo;

        public Builder() {

        }

        public CreateCategoryRequest.Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public CreateCategoryRequest.Builder name(String name) {
            this.name = name;
            return this;
        }

        public CreateCategoryRequest.Builder logo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        public CreateCategoryRequest build() {
            return new CreateCategoryRequest(this);
        }
    }

    public static CreateCategoryRequest.Builder builder() {
        return new CreateCategoryRequest.Builder();
    }

    @Override
    public String toString() {
        return "CreateCategoryRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo=" + Arrays.toString(logo) +
                '}';
    }
}
