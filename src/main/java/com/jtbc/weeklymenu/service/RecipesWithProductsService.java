package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.RecipeWithProductDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;

import java.util.List;


public interface RecipesWithProductsService {
    RecipesWithProducts findById(Long id);
    List<RecipesWithProducts> findAll();





}
