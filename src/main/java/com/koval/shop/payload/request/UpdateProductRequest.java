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
}
