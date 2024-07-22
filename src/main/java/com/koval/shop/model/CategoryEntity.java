package com.koval.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koval.shop.annotation.UUIDVersion7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name = "category")
@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(generator = "uuid7")
    @UUIDVersion7Generator(name = "uuid7")
    private UUID id;

    private String name;

    private byte[] logo;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductEntity> products = new ArrayList<>();

    public CategoryEntity() {

    }

    private CategoryEntity(Builder builder) {
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
        CategoryEntity categoryEntity = (CategoryEntity) o;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> productEntities) {
        this.products = productEntities;
    }

    public static class Builder {

        private UUID id;
        private String name;
        private byte[] logo;
        private List<ProductEntity> products = new ArrayList<>();

        public Builder() {

        }

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

        public Builder product(ProductEntity productEntity) {
            this.products.add(productEntity);
            return this;
        }

        public CategoryEntity build() {
            return new CategoryEntity(this);
        }
    }

    public static CategoryEntity.Builder builder() {
        return new CategoryEntity.Builder();
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo=" + Arrays.toString(logo) +
                '}';
    }
}
