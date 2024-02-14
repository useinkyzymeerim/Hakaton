package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDTO;
import com.jtbc.weeklymenu.entity.Recipes;

import java.util.List;


public interface RecipesService {
    Recipes findById(Long id);
    List<RecipesDTO> getAllRecipes();
    List<RecipeDetailsDTO> findRecipeDetails(Long recipeId);
     List<RecipesDTO> findByProduct(String productName);
    List<RecipesDTO> findByRecipeNameIgnoreCase(String recipeName);

}

