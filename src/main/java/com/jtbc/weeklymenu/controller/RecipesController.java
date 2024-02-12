package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.service.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipeService;

    @GetMapping("/getAllRecipes")
    public List<RecipesDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }



       @GetMapping("/recipeDetails/{recipeId}")
    public ResponseEntity<List<RecipeDetailsDTO>> getRecipeDetails(@PathVariable Long recipeId) {
        List<RecipeDetailsDTO> recipeDetails = recipeService.findRecipeDetails(recipeId);
        return ResponseEntity.ok(recipeDetails);
    }

    @PostMapping("/createRecipe")
    public ResponseEntity<Recipes> createRecipes(@RequestBody Recipes recipes) {
        Recipes createdRecipe = recipeService.create(recipes);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Recipes> updateRecipes(@RequestParam("id") Long id, @RequestBody Recipes updatedRecipe) {
        Recipes existingRecipe = recipeService.findById(id);

        if (existingRecipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        existingRecipe.setNameOfFood(updatedRecipe.getNameOfFood());


        Recipes updatedRecipeEntity = recipeService.update(existingRecipe);
        return new ResponseEntity<>(updatedRecipeEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") Long id) {
        Recipes existingRecipe = recipeService.findById(id);

        if (existingRecipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{recipeId}/unique-details")
    public ResponseEntity<List<RecipeDetailsDTO>> getUniqueRecipeDetails(@PathVariable Long recipeId) {
        List<RecipeDetailsDTO> uniqueDetails = recipeService.findUniqueRecipeDetails(recipeId);
        return ResponseEntity.ok(uniqueDetails);
    }
}






