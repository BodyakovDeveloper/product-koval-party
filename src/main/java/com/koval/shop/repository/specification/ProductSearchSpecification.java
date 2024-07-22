package com.koval.shop.repository.specification;

import com.koval.shop.model.ProductEntity;
import com.koval.shop.payload.request.ProductSearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSearchSpecification {

    public ProductSearchSpecification() {

    }

    public static Specification<ProductEntity> byNameAndCategories(ProductSearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.conjunction();

            if (searchRequest.getProductName() != null && !searchRequest.getProductName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, byProductName(searchRequest.getProductName(), root, criteriaBuilder));
            }

            if (searchRequest.getCategoryNames() != null && !searchRequest.getCategoryNames().isEmpty()) {
                List<String> lowerCategoryNames = searchRequest.getCategoryNames().stream()
                        .map(String::toLowerCase)
                        .toList();
                predicate = criteriaBuilder.and(predicate, byCategoryNames(lowerCategoryNames, root, criteriaBuilder));
            }

            root.fetch("category", JoinType.LEFT);

            return predicate;
        };
    }

    private static Predicate byProductName(String productName, Root<ProductEntity> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productName.toLowerCase() + "%");
    }

    private static Predicate byCategoryNames(List<String> categoryNames, Root<ProductEntity> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lower(root.get("category").get("name")).in(categoryNames);
    }
}
