package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.ProductWithQuantityDTO;
import com.jtbc.weeklymenu.dto.RecipeWithProductsDTO;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface RecipesWithProductsService {
    RecipesWithProducts create(RecipesWithProducts recipesWithProducts);
    RecipesWithProducts findById(Long id);
    RecipesWithProducts update(RecipesWithProducts recipesWithProducts);
    void delete(Long id);
    List<RecipesWithProducts> findAll();

}
