package com.koval.shop.payload.dto;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class CategoryDto {

    private UUID id;
    private String name;
    private byte[] logo;

    public CategoryDto() {

    }

    public CategoryDto(UUID id, String name, byte[] logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
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
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo=" + Arrays.toString(logo) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryDto that = (CategoryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }

    public static class Builder {

        private UUID id;
        private String name;
        private byte[] logo;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder logo(byte[] logo) {
            this.logo = logo;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(id, name, logo);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
