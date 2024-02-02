package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfFood;
    // Однонаправленное отношение один ко многим
    @OneToMany(mappedBy = "recipe")
    private Set<RecipesWithProducts> recipesWithProducts;

    // Многие ко многим с Menu
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    // Многие ко многим с Users
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

