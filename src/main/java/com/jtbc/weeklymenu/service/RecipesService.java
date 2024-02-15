package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Recipes;

import java.util.List;


public interface RecipesService {
    List<RecipesDto> getAllRecipes();
    List<RecipeDetailsDTO> findRecipeDetails(Long recipeId);
     List<RecipesDto> findByProduct(String productName);
    List<RecipesDto> findByRecipeNameIgnoreCase(String recipeName);
    Long create(RecipesDto recipesDto) throws NullPointerException;
    String delete(Long id);
    RecipesDto findById(Long id);
    List<RecipesDto> findAll () throws Exception;

}

