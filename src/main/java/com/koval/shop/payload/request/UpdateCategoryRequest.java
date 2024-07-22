package com.koval.shop.payload.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

public class UpdateCategoryRequest {

    private UUID id;
    private String name;
    private MultipartFile image;

    public UpdateCategoryRequest() {

    }

    public UpdateCategoryRequest(UUID id, MultipartFile image) {
        this.id = id;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCategoryRequest that = (UpdateCategoryRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryChangeLogoRequest{" +
                "id=" + id +
                '}';
    }
}
