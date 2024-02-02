package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.ProductWithQuantityDTO;
import com.jtbc.weeklymenu.dto.RecipeWithProductsDTO;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipesWithProductsService {
    private final RecipesWithProductsRepo recipesWithProductsRepo;
    private final RecipesRepo recipeRepo;
    public RecipesWithProducts saveRecipesWithProducts(RecipesWithProducts recipesWithProducts) {
        return recipesWithProductsRepo.save(recipesWithProducts);
    }

    public List<RecipesWithProducts> getAllRecipesWithProducts() {
        return recipesWithProductsRepo.findAll();
    }

    public RecipesWithProducts getRecipesWithProductsById(Long id) {
        return recipesWithProductsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipesWithProducts not found"));
    }

    public List<RecipeWithProductsDTO> getRecipesWithProducts() {
        List<Recipes> recipes = recipeRepo.findAll();
        List<RecipeWithProductsDTO> result = new ArrayList<>();

        for (Recipes recipe : recipes) {
            List<RecipesWithProducts> recipesWithProductsList = recipesWithProductsRepo.findByRecipe_Id(recipe.getId());

            RecipeWithProductsDTO dto = new RecipeWithProductsDTO();
            dto.setRecipeId(recipe.getId());
            dto.setRecipeName(recipe.getNameOfFood());

            List<ProductWithQuantityDTO> productWithQuantityDTOList = new ArrayList<>();

            for (RecipesWithProducts recipesWithProducts : recipesWithProductsList) {
                ProductWithQuantityDTO productDTO = new ProductWithQuantityDTO();
                productDTO.setProductName(recipesWithProducts.getProduct().getProductName());
                productDTO.setQuantity(recipesWithProducts.getQuantityOfProduct());
                productWithQuantityDTOList.add(productDTO);
            }

            dto.setProducts(productWithQuantityDTOList);
            result.add(dto);
        }

        return result;
    }


}
