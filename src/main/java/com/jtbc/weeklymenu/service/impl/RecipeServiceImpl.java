package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.repo.RecipesRepo;
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
    @Override
    public Recipes create(Recipes recipes) {
        Recipes recipes1 = new Recipes();

        return recipesRepo.save(recipes);
    }

    @Override
    public Recipes findById(Long id) {
        return recipesRepo.findById(id).orElse(null);
    }

    @Override
    public Recipes update(Recipes recipes) {
        Optional<Recipes> recipesOptional = recipesRepo.findById(recipes.getId());
        if (recipesOptional.isPresent()) {
            Recipes existingRecipes = recipesOptional.get();
            existingRecipes.setNameOfFood(recipes.getNameOfFood());
            return recipesRepo.save(existingRecipes);
        } else {
            throw new RuntimeException("Menu not found");
        }
    }

    @Override
    public void delete (Long id){
        Recipes existingRecipes = recipesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipes not found with id: " + id));

        recipesRepo.delete(existingRecipes);
    }


    @Override
    public List<RecipesDto> getAllRecipes () {
        List<Recipes> recipes = recipesRepo.findAll();
        return recipes.stream().map(recipes1 -> {
                RecipesDto dto = new RecipesDto();
                dto.setRecipeId(recipes1.getId());
                dto.setNameOfFood(recipes1.getNameOfFood());
                return dto;}).collect(Collectors.toList());

    }

    @Override

    public List<RecipeDetailsDTO> findRecipeDetails(Long recipeId) {
        return recipesRepo.findRecipeDetails(recipeId);
    }
    @Override
    public List<RecipeDetailsDTO> findUniqueRecipeDetails(Long recipeId) {
        Set<RecipeDetailsDTO> uniqueRecipes = new HashSet<>();
        List<RecipeDetailsDTO> allRecipes = recipesRepo.findRecipeDetails(recipeId);

        for (RecipeDetailsDTO recipe : allRecipes) {
            if (!uniqueRecipes.contains(recipe)) {
                uniqueRecipes.add(recipe);
            }
        }

        return new ArrayList<>(uniqueRecipes);
    }


}
