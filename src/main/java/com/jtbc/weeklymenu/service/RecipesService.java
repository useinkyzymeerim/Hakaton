package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeWithProductsDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface RecipesService {
    Recipes create(Recipes recipe);
    Recipes findById(Long id);
    Recipes update(Recipes recipe);
    void delete(Long id);
    List<Recipes> findAll();
}

