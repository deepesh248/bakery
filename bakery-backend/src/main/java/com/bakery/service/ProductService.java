package com.bakery.service;

import com.bakery.model.Product;
import com.bakery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        return productRepository.findById(id).map(product -> {
            product.setName(updated.getName());
            product.setDescription(updated.getDescription());
            product.setPrice(updated.getPrice());
            product.setQuantity(updated.getQuantity());
            product.setImageUrl(updated.getImageUrl());
            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
