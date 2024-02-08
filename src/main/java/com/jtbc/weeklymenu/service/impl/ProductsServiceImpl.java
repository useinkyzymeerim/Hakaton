package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import com.jtbc.weeklymenu.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
