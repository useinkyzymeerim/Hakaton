package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.dto.RecipeWithProductDTO;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.service.ProductService;
import com.jtbc.weeklymenu.service.RecipesWithProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipeWithProducts")
public class RecipeWithProductsController {
    private final RecipesWithProductsService recipesWithProductsService;
    private final ProductService productService;
    @GetMapping("/getAll")
    public ResponseEntity<List<RecipesWithProducts>> getAllRecipesWithProducts() {
        List<RecipesWithProducts> recipesWithProducts = recipesWithProductsService.findAll();
        return new ResponseEntity<>(recipesWithProducts, HttpStatus.OK);
    }
    // GET запрос для получения записи RecipeWithProducts по ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<RecipesWithProducts> getRecipeWithProductsById(@PathVariable("id") Long id) {
        RecipesWithProducts recipeWithProducts = recipesWithProductsService.findById(id);
        if (recipeWithProducts != null) {
            return new ResponseEntity<>(recipeWithProducts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("create")
    public ResponseEntity<RecipesWithProducts> createRecipeWithProducts(@RequestBody RecipesWithProducts recipeWithProducts) {
        RecipesWithProducts createdRecipeWithProducts = recipesWithProductsService.create(recipeWithProducts);
        return new ResponseEntity<>(createdRecipeWithProducts, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RecipesWithProducts> updateRecipeWithProducts(@PathVariable("id") Long id,
                                                                        @RequestBody RecipesWithProducts updatedRecipeWithProducts) {
        RecipesWithProducts existingRecipesWithProducts = recipesWithProductsService.findById(id);

        if (existingRecipesWithProducts == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        existingRecipesWithProducts.setRecipe(updatedRecipeWithProducts.getRecipe());
        existingRecipesWithProducts.setProduct(updatedRecipeWithProducts.getProduct());
        existingRecipesWithProducts.setQuantityOfProduct(updatedRecipeWithProducts.getQuantityOfProduct());

        RecipesWithProducts updatedRecipesWithProductsEntity = recipesWithProductsService.update(existingRecipesWithProducts);

            return new ResponseEntity<>(updatedRecipesWithProductsEntity,HttpStatus.OK);

    }
    @DeleteMapping("delete/{id}")

    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id) {
        RecipesWithProducts existingRecipesWithProducts = recipesWithProductsService.findById(id);

        if (existingRecipesWithProducts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipesWithProductsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
