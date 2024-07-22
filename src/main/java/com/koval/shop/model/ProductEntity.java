package com.koval.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.koval.shop.annotation.UUIDVersion7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "uuid7")
    @UUIDVersion7Generator(name = "uuid7")
    private UUID id;

    private String name;

    private byte[] logo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryEntity category;

    public ProductEntity() {

    }

    private ProductEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
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
        ProductEntity ProductEntity = (ProductEntity) o;
        return Objects.equals(id, ProductEntity.id);
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity categoryEntity) {
        this.category = categoryEntity;
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
        private CategoryEntity category;

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

        public Builder category(CategoryEntity categoryEntity) {
            this.category = categoryEntity;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
