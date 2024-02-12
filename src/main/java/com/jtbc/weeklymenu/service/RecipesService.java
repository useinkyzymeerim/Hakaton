package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Recipes;

import java.util.List;


public interface RecipesService {
    Recipes create(Recipes recipe);
    Recipes findById(Long id);
    Recipes update(Recipes recipe);
    void delete(Long id);

    List<RecipesDto> getAllRecipes();
    public List<RecipeDetailsDTO> findRecipeDetails(Long recipeId);

    List<RecipeDetailsDTO> findUniqueRecipeDetails(Long recipeId);


}

