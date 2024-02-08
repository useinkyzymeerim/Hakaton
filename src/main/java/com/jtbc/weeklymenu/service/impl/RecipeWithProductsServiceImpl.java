package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.RecipesWithProductsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeWithProductsServiceImpl implements RecipesWithProductsService {
    private final RecipesWithProductsRepo recipesWithProductsRepo;
    @Override
    public RecipesWithProducts create(RecipesWithProducts recipesWithProducts) {
        return recipesWithProductsRepo.save(recipesWithProducts);
    }

    @Override
    public RecipesWithProducts findById(Long id) {
        return recipesWithProductsRepo.findById(id).orElse(null);
    }

    @Override
    public RecipesWithProducts update(RecipesWithProducts recipesWithProducts) {
        Optional<RecipesWithProducts> recipesWithProductsOptional = recipesWithProductsRepo.findById(recipesWithProducts.getId());
        if (recipesWithProductsOptional.isPresent()) {
            RecipesWithProducts existingrecipesWithProducts = recipesWithProductsOptional.get();
            existingrecipesWithProducts.setProduct(recipesWithProducts.getProduct());
            existingrecipesWithProducts.setRecipe(recipesWithProducts.getRecipe());
            existingrecipesWithProducts.setQuantityOfProduct(recipesWithProducts.getQuantityOfProduct());
            return recipesWithProductsRepo.save(recipesWithProducts);
        } else {
            throw new RuntimeException("Recipes With Products not found");
        }
    }

    @Override
    public void delete (Long id){
        RecipesWithProducts existingrecipesWithProducts = recipesWithProductsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipes With Products not found with id: " + id));

        recipesWithProductsRepo.delete(existingrecipesWithProducts);
    }


    @Override
    public List<RecipesWithProducts> findAll () {
        return recipesWithProductsRepo.findAll();
    }

}
