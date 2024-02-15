package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.service.RecipesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает весь список Рецептов")
    @GetMapping("/getAllRecipes")
    public List<RecipesDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Рецепты с продуктами по ID")
       @GetMapping("/recipeDetails/{recipeId}")
    public ResponseEntity<List<RecipeDetailsDTO>> getRecipeDetails(@PathVariable Long recipeId) {
        List<RecipeDetailsDTO> recipeDetails = recipeService.findRecipeDetails(recipeId);
        return ResponseEntity.ok(recipeDetails);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Продукт с названием")
    @GetMapping("/getByProductName")
    public ResponseEntity<List<RecipesDto>> getRecipesByProduct(@RequestParam String productName) {
        List<RecipesDto> recipeDTOList = recipeService.findByProduct(productName);
        return ResponseEntity.ok(recipeDTOList);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Рецепты с названием")
    @GetMapping("/recipes-by-name")
    public ResponseEntity<List<RecipesDto>> getRecipesByNameIgnoreCase(@RequestParam String recipeName) {
        List<RecipesDto> recipeDTOList = recipeService.findByRecipeNameIgnoreCase(recipeName);
        return ResponseEntity.ok(recipeDTOList);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты умпешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для Create Recipes")
    @PostMapping("/createRecipes")
    public ResponseEntity<Long> createRecipes(@RequestBody RecipesDto recipesDto) throws NullPointerException {
        try {
            Long savedId = recipeService.create(recipesDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты умпешно удалены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для Delete Recipes")

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        String result = recipeService.delete(id);
        return ResponseEntity.ok(result);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт умпешно получен",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Рецепе с ID")

    @GetMapping("/{id}")

    public RecipesDto findById(@PathVariable Long id) throws NullPointerException{
        try{
            return recipeService.findById(id);

        }catch (NullPointerException exceptione){
            System.out.println(exceptione.getMessage());
            return new RecipesDto();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все рецепты умпешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Все Рецепе")

    @GetMapping("/all")
    public List<RecipesDto> getAll() throws Exception{
        return recipeService.findAll();
    }


}






