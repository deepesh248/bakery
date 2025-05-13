package com.bakery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.model.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Optional<ProductDetail> findByProductId(Long productId);
}
