package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import com.jtbc.weeklymenu.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService {
    private final ProductRepo productRepo;


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
    public List<Products> findAll () {
        return productRepo.findAll();
    }
}
