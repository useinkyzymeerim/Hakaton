package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class RecipesWithProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipes recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    private Integer quantityOfProduct;


}
