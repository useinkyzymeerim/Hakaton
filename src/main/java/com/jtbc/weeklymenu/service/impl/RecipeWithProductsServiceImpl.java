package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.dto.RecipeWithProductDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.RecipesService;
import com.jtbc.weeklymenu.service.RecipesWithProductsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeWithProductsServiceImpl implements RecipesWithProductsService {
    private final RecipesWithProductsRepo recipesWithProductsRepo;
    @Override
    public RecipesWithProducts findById(Long id) {
        return recipesWithProductsRepo.findById(id).orElse(null);
    }

    @Override
    public List<RecipesWithProducts> findAll () {
        return recipesWithProductsRepo.findAll();
    }



}

