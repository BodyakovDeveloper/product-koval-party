package com.koval.shop.repository;

import com.koval.shop.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    @Query("""
            SELECT c FROM CategoryEntity c LEFT JOIN FETCH c.products WHERE c.id = :id
            """)
    Optional<CategoryEntity> findByIdWithProducts(UUID id);
}
