package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepo extends JpaRepository<Recipes,Long> {
    Recipes findByNameOfFood(String nameOfFood);
}
