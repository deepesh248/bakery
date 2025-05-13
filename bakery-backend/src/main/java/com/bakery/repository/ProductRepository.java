package com.bakery.repository;

import com.bakery.model.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {}