package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.ProductRepo;
import com.jtbc.weeklymenu.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public Products create(Products products) {
        return productRepo.save(products);
    }

    @Override
    public Products findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<String> getRecipesByProductName(String productName) {
        Products product = productRepo.getProductByProductName(productName);
        if (product != null) {
            return product.getRecipesWithProducts()
                    .stream()
                    .map(recipesWithProducts -> recipesWithProducts.getRecipe().getNameOfFood())
                    .collect(Collectors.toList());
        } else {
            return null; // или бросить исключение или вернуть пустой список
        }
    }
    @Override
    public Products update(Products products) {
        Optional<Products> productsOptional = productRepo.findById(products.getId());
        if (productsOptional.isPresent()) {
            Products existingProduct = productsOptional.get();
            existingProduct.setProductName(products.getProductName());
            return productRepo.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void delete (Long id){
        Products existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        productRepo.delete(existingProduct);
    }


    @Override
    public List<Products> findAll () {
        return productRepo.findAll();
    }
}
