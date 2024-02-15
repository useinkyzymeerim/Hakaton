package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfMenu;
    private Date removeDate;
    @OneToMany(mappedBy = "menu")
    private List<Recipes> recipes = new ArrayList<>();
}

