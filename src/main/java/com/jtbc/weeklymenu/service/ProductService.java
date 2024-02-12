package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Products create(Products product);
    Products findById(Long id);
    List<String> getRecipesByProductName(String productName);
    Products update(Products product);
    void delete(Long id);
    List<Products> findAll();
    }
