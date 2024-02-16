package com.jtbc.weeklymenu.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private Date removeDate;

    @OneToMany(mappedBy = "user")
    private Set<Recipes> recipes;
}
