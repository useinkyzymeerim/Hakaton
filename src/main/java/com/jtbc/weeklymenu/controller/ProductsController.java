package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import com.jtbc.weeklymenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    @GetMapping("/getAll")
    public List<Products> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public Products getByProductId(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/recipes-by-product-name")
    public ResponseEntity<List<String>> getRecipesByProductName(@RequestParam String productName) {
        List<String> recipeNames = productService.getRecipesByProductName(productName);
        if (recipeNames != null) {
            return ResponseEntity.ok(recipeNames);
        } else {
            return ResponseEntity.notFound().build(); // вернуть 404, если продукт не найден
        }
    }

}
