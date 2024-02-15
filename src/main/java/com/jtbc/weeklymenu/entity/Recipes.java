package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfFood;
    private Date removeDate;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipesWithProducts> recipesWithProducts;


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

