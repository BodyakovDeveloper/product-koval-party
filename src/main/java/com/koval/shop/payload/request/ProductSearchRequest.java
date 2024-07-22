package com.koval.shop.payload.request;

import java.util.List;

public class ProductSearchRequest {

    private String productName;
    private List<String> categoryNames;

    public ProductSearchRequest() {

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


}
