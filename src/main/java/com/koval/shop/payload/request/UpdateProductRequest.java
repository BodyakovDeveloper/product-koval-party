package com.koval.shop.payload.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

public class UpdateProductRequest {

    private UUID id;
    private String name;
    private MultipartFile logo;

    public UpdateProductRequest() {

    }

    private UpdateProductRequest(UpdateProductRequest.Builder builder) {
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

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateProductRequest that = (UpdateProductRequest) o;
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
        return "UpdateProductNameRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Builder {
        private UUID id;
        private String name;
        private MultipartFile logo;

        public Builder() {

        }

        public UpdateProductRequest.Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public UpdateProductRequest.Builder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateProductRequest.Builder logo(MultipartFile logo) {
            this.logo = logo;
            return this;
        }

        public UpdateProductRequest build() {
            return new UpdateProductRequest(this);
        }
    }

    public static UpdateProductRequest.Builder builder() {
        return new UpdateProductRequest.Builder();
    }
}
