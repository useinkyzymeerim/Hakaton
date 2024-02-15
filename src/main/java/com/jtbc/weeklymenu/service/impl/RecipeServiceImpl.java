package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.RecipesService;
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
    public List<RecipesDto> getAllRecipes() {
        return null;
    }

    @Override
    public List<RecipeDetailsDTO> findRecipeDetails(Long recipeId) {
        return recipesRepo.findRecipeDetails(recipeId);
    }


    @Override
    public List<RecipesDto> findByProduct(String productName) {
        List<RecipesWithProducts> recipesWithProductsList = recipesWithProductsRepo.findByProduct_ProductName(productName);


        List<RecipesDto> recipesDTOList = recipesWithProductsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return recipesDTOList;
    }

    private RecipesDto convertToDTO(RecipesWithProducts recipesWithProducts) {
        RecipesDto recipeDTO = new RecipesDto();
        recipeDTO.setId(recipesWithProducts.getRecipe().getId());
        recipeDTO.setNameOfFood(recipesWithProducts.getRecipe().getNameOfFood());

        return recipeDTO;
    }
    @Override
    public List<RecipesDto> findByRecipeNameIgnoreCase(String recipeName) {
        List<Recipes> recipes = recipesRepo.findByNameOfFoodIgnoreCase(recipeName);
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RecipesDto convertToDTO(Recipes recipe) {
        RecipesDto recipeDTO = new RecipesDto();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setNameOfFood(recipe.getNameOfFood());

        return recipeDTO;
    }

    @Override
    public Long create(RecipesDto recipesDto) throws NullPointerException {
        if (recipesDto.getId() == null) {
            Recipes recipes = new Recipes();
            recipes.setNameOfFood(recipesDto.getNameOfFood());
            recipes = recipesRepo.save(recipes);
            return recipes.getId();
        } else {
            return update(recipesDto);
        }
    }

    private Long update(RecipesDto recipesDto) throws NullPointerException {
        Optional<Recipes> optionalRecipes = recipesRepo.findById(recipesDto.getId());

        if (optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            recipes.setNameOfFood(recipesDto.getNameOfFood());
            return recipesRepo.save(recipes).getId();

        } else throw new NullPointerException(String.format("Рецепт с id %s не найдена", recipesDto.getId()));
    }

    public String delete(Long id) {
        Optional<Recipes> optionalRecipes = recipesRepo.findById(id);

        if (optionalRecipes.isPresent()) {
            Recipes recipes = optionalRecipes.get();
            recipes.setRemoveDate(new Date(System.currentTimeMillis()));
            recipesRepo.save(recipes);

        } else throw new NullPointerException(String.format("Рецепт с id %s не найдена", id));
        return "Deleted";
    }

    public List<RecipesDto> findAll () throws Exception {
        var list = recipesRepo.findAllAndBOrderByRemoveDateIsNull();

        List<RecipesDto> dtoList= new ArrayList<>();

        for (Recipes recipes : list) {
            var dto = new RecipesDto(
                    recipes.getId(),
                    recipes.getNameOfFood()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }
    public RecipesDto findById(Long id) {
        Optional<Recipes> recipes = recipesRepo.findRecipesByRemoveDateIsNullAndId(id);

        var dto = new RecipesDto();
        if (recipes.isPresent()) {
            dto = new RecipesDto(
                    recipes.get().getId(),
                    recipes.get().getNameOfFood()
            );
        } else throw new NullPointerException(String.format("Рецепт с id %s не найдена", id));
        return dto;
    }


}
