package com.koval.shop.payload.request;

import java.util.List;

public class ProductSearchRequest {

    private String productName;
    private List<String> categoryNames;

    public ProductSearchRequest() {

    }

    private ProductSearchRequest(ProductSearchRequest.Builder builder) {
        this.productName = builder.productName;
        this.categoryNames = builder.categoryNames;
    }

    public ProductSearchRequest(String productName, List<String> categoryNames) {
        this.productName = productName;
        this.categoryNames = categoryNames;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    @Override
    public String toString() {
        return "ProductSearchRequest{" +
                "productName='" + productName + '\'' +
                ", categoryNames=" + categoryNames +
                '}';
    }


    public static class Builder {
        private String productName;
        private List<String> categoryNames;

        public Builder() {

        }

        public ProductSearchRequest.Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductSearchRequest.Builder categoryNames(List<String> categoryNames) {
            this.categoryNames = categoryNames;
            return this;
        }

        public ProductSearchRequest build() {
            return new ProductSearchRequest(this);
        }
    }

    public static ProductSearchRequest.Builder builder() {
        return new ProductSearchRequest.Builder();
    }
}
