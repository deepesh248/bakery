package com.bakery.controller;

import com.bakery.model.Product;
import com.bakery.model.ProductDetail;
import com.bakery.model.Review;
import com.bakery.repository.ProductDetailRepository;
import com.bakery.repository.ProductRepository;
import com.bakery.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {

    @Autowired
    private ProductDetailRepository productDetailRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductRepository productRepo; 

   
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetail> getDetail(@PathVariable("productId") Long productId) {
        return productDetailRepo.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

 
    @PostMapping("/{productId}")
    public ResponseEntity<ProductDetail> addDetail(@PathVariable("productId") Long productId, @RequestBody ProductDetail detail) {
        // Ensure that the product exists before adding the detail
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        detail.setProduct(product); 
        ProductDetail saved = productDetailRepo.save(detail);
        return ResponseEntity.ok(saved);
    }


    @PostMapping("/{productId}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable("productId") Long productId, @RequestBody Review review) {
        ProductDetail detail = productDetailRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));

        review.setProductDetail(detail); 
        Review saved = reviewRepo.save(review);
        return ResponseEntity.ok(saved);
    }
    
    public void deleteProduct(Long id) {
    
        productDetailRepo.findByProductId(id).ifPresent(detail -> {
            reviewRepo.deleteAllByProductDetailId(detail.getId());
            productDetailRepo.delete(detail);
        });

      
        productRepo.deleteById(id);
    }

}
