package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeWithProductsDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipesService {
    private final RecipesRepo recipesRepo;

    public Recipes saveRecipe(Recipes recipe) {
        return recipesRepo.save(recipe);
    }

    public List<Recipes> getAllRecipes() {
        return recipesRepo.findAll();
    }

    public Recipes getRecipeById(Long recipeId) {
        return recipesRepo.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }
    public Recipes updateRecipes(Recipes updateRecipes){
        Optional<Recipes> recipesOptional = recipesRepo.findById(updateRecipes.getId());
        if(recipesOptional.isPresent()){
            Recipes existingRecipes = recipesOptional.get();

            existingRecipes.setNameOfFood(existingRecipes.getNameOfFood());
            return recipesRepo.save(existingRecipes);
        }else {
            throw new RuntimeException("Recipe not found");
        }
    }
    public void deleteRecipeById(Long recipeId) {
        recipesRepo.deleteById(recipeId);
    }

    public List<RecipeWithProductsDTO> getRecipesWithProducts() {
        return null;
    }
}


