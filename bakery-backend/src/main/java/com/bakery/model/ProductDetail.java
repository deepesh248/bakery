package com.bakery.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean eggless;
    private String ingredients;
    private String description;
    private String weight;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}
