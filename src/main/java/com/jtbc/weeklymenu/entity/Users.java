package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Set;

@Entity
@Table
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String userName;
    private String password;
    // Однонаправленное отношение один ко многим
    @OneToMany(mappedBy = "user")
    private Set<Recipes> recipes;
}
