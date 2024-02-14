package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDTO;
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
    public List<RecipesDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }



       @GetMapping("/recipeDetails/{recipeId}")
    public ResponseEntity<List<RecipeDetailsDTO>> getRecipeDetails(@PathVariable Long recipeId) {
        List<RecipeDetailsDTO> recipeDetails = recipeService.findRecipeDetails(recipeId);
        return ResponseEntity.ok(recipeDetails);
    }

    @GetMapping("/getByProductName")
    public ResponseEntity<List<RecipesDTO>> getRecipesByProduct(@RequestParam String productName) {
        List<RecipesDTO> recipeDTOList = recipeService.findByProduct(productName);
        return ResponseEntity.ok(recipeDTOList);
    }
    @GetMapping("/recipes-by-name")
    public ResponseEntity<List<RecipesDTO>> getRecipesByNameIgnoreCase(@RequestParam String recipeName) {
        List<RecipesDTO> recipeDTOList = recipeService.findByRecipeNameIgnoreCase(recipeName);
        return ResponseEntity.ok(recipeDTOList);
    }


}






