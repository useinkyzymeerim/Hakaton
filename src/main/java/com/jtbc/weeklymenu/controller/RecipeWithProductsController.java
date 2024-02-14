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





}
