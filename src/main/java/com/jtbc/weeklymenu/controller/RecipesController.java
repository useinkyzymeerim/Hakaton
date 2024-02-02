package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.RecipeWithProductsDTO;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.service.RecipesService;
import com.jtbc.weeklymenu.service.RecipesWithProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipeService;


 /*   @GetMapping("/{recipeId}/withProducts")
    public ResponseEntity<?> getRecipeWithProducts(@PathVariable Long recipeId) {
        try {
            RecipeWithProductsDTO recipeWithProductsDTO = recipesWithProductsService.getRecipeWithProductsDTO(recipeId);
            return new ResponseEntity<>(recipeWithProductsDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to get recipe with products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/withProducts")
    public ResponseEntity<List<RecipeWithProductsDTO>> getRecipesWithProducts() {
        List<RecipeWithProductsDTO> recipesWithProductsDTOList = recipeService.getRecipesWithProducts();
        return new ResponseEntity<>(recipesWithProductsDTOList, HttpStatus.OK);
    }

}

