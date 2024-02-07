package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfMenu;
    // Однонаправленное отношение один ко многим
    @OneToMany(mappedBy = "menu")
    private List<Recipes> recipes = new ArrayList<>();
}

