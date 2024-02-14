package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDTO;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.RecipesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipesService {
    private final RecipesRepo recipesRepo;
    private final RecipesWithProductsRepo recipesWithProductsRepo;
    @Override
    public Recipes findById(Long id) {
        return recipesRepo.findById(id).orElse(null);
    }
    @Override
    public List<RecipesDTO> getAllRecipes () {
        List<Recipes> recipes = recipesRepo.findAll();
        return recipes.stream().map(recipes1 -> {
                RecipesDTO dto = new RecipesDTO();
                dto.setRecipeId(recipes1.getId());
                dto.setNameOfFood(recipes1.getNameOfFood());
                return dto;}).collect(Collectors.toList());
    }
    @Override
    public List<RecipeDetailsDTO> findRecipeDetails(Long recipeId) {
        return recipesRepo.findRecipeDetails(recipeId);
    }


    @Override
    public List<RecipesDTO> findByProduct(String productName) {
        List<RecipesWithProducts> recipesWithProductsList = recipesWithProductsRepo.findByProduct_ProductName(productName);

        // Преобразование списка объектов RecipesWithProducts в список объектов RecipeDTO
        List<RecipesDTO> recipesDTOList = recipesWithProductsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return recipesDTOList;
    }

    private RecipesDTO convertToDTO(RecipesWithProducts recipesWithProducts) {
        RecipesDTO recipeDTO = new RecipesDTO();
        recipeDTO.setRecipeId(recipesWithProducts.getRecipe().getId());
        recipeDTO.setNameOfFood(recipesWithProducts.getRecipe().getNameOfFood());

        return recipeDTO;
    }
    @Override
    public List<RecipesDTO> findByRecipeNameIgnoreCase(String recipeName) {
        List<Recipes> recipes = recipesRepo.findByNameOfFoodIgnoreCase(recipeName);
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RecipesDTO convertToDTO(Recipes recipe) {
        RecipesDTO recipeDTO = new RecipesDTO();
        recipeDTO.setRecipeId(recipe.getId());
        recipeDTO.setNameOfFood(recipe.getNameOfFood());

        return recipeDTO;
    }


}
