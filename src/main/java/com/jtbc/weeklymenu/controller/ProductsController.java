package com.jtbc.weeklymenu.controller;

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
    @PostMapping("/createProducts")
    public ResponseEntity<Products> createProducts(@RequestBody Products products) {
        Products createdProducts = productService.create(products);
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProducts(@RequestParam("id") Long id, @RequestBody Products updatedProduct) {
        Products existingProducts = productService.findById(id);

        if (existingProducts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        existingProducts.setProductName(updatedProduct.getProductName());


        Products updatedProductsEntity = productService.update(existingProducts);
        return new ResponseEntity<>(updatedProductsEntity, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable("id") Long id) {
        Products existingProducts = productService.findById(id);

        if (existingProducts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
