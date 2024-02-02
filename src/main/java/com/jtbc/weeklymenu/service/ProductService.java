package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepo productsRepo;
    public Products saveProduct(Products product) {
        return productsRepo.save(product);
    }

    public List<Products> getAllProducts() {
        return productsRepo.findAll();
    }

    public Products getProductById(Long productId) {
        return productsRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Products updateProducts(Products updateProducts){
        Optional<Products> productsOptional = productsRepo.findById(updateProducts.getId());
        if(productsOptional.isPresent()){
            Products existingProducts = productsOptional.get();

            existingProducts.setProductName(existingProducts.getProductName());
            return productsRepo.save(existingProducts);
        }else {
            throw new RuntimeException("Products not found");
        }
    }

    public void deleteProductById(Long productId) {
        productsRepo.deleteById(productId);
    }
}
