package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.ProductDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.repo.ProductRepo;
import com.jtbc.weeklymenu.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override
    public Long create(ProductDTO productDTO) throws NullPointerException {
        if (productDTO.getId() == null) {
            Products products = new Products();
            products.setProductName(productDTO.getProductName());
            products = productRepo.save(products);
            return products.getId();
        } else {
            return update(productDTO);
        }
    }

    private Long update(ProductDTO productDTO) throws NullPointerException {
        Optional<Products> optionalProduct = productRepo.findById(productDTO.getId());

        if (optionalProduct.isPresent()) {
            Products products = optionalProduct.get();
            products.setProductName(productDTO.getProductName());
            return productRepo.save(products).getId();

        } else throw new NullPointerException(String.format("Продук с id %s не найдена", productDTO.getId()));
    }

    public String delete(Long id) {
        Optional<Products> optionalProducts = productRepo.findById(id);

        if (optionalProducts.isPresent()) {
            Products products = optionalProducts.get();
            products.setRemoveDate(new Date(System.currentTimeMillis()));
            productRepo.save(products);

        } else throw new NullPointerException(String.format("Продукт с id %s не найдена", id));
        return "Deleted";
    }

    public List<ProductDTO> findAll () throws Exception {
        var list = productRepo.findAllAndBOrderByRemoveDateIsNull();

        List<ProductDTO> dtoList = new ArrayList<>();

        for (Products products : list) {
            var dto = new ProductDTO(
                    products.getId(),
                    products.getProductName()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }
    public ProductDTO findById(Long id) {
        Optional<Products> products = productRepo.findProductByRemoveDateIsNullAndId(id);

        var dto = new ProductDTO();
        if (products.isPresent()) {
            dto = new ProductDTO(
                    products.get().getId(),
                    products.get().getProductName()
            );
        } else throw new NullPointerException(String.format("Продукт с id %s не найдена", id));
        return dto;
    }

}
