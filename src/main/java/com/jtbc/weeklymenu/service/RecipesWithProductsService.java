package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeWithProductDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;

import java.util.List;


public interface RecipesWithProductsService {
    RecipesWithProducts create(RecipesWithProducts recipesWithProducts);
    RecipesWithProducts findById(Long id);
    RecipesWithProducts update(RecipesWithProducts recipesWithProducts);
    void delete(Long id);
    List<RecipesWithProducts> findAll();

    RecipeWithProductDTO mapToDTO(RecipesWithProducts rp);


}
