package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.ProductDTO;
import com.jtbc.weeklymenu.dto.ProductWithRecipesDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO findById(Long id);

    Long create(ProductDTO productDTO) throws NullPointerException;

    String delete(Long id);

    List<ProductDTO> findAll() throws Exception;
}

