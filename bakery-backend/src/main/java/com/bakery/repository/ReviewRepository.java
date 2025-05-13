package com.bakery.repository;

import com.bakery.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductDetailId(Long productDetailId);
    void deleteAllByProductDetailId(Long productDetailId);
}