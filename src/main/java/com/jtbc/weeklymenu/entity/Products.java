package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    @OneToMany(mappedBy = "product")
    private Set<RecipesWithProducts> recipesWithProducts;
}




