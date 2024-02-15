package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.ProductDTO;
import com.jtbc.weeklymenu.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает весь список Продукты")
    @GetMapping("/all")
    public List<ProductDTO> getAllProduct() throws Exception{
        return productService.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно создан",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут создает Продукт")
    @PostMapping("/createProduct")
    public ResponseEntity<Long> createProduct(@RequestBody ProductDTO productDTO) throws NullPointerException {
        try {
            Long savedId = productService.create(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно удален",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут удаляет Продукт")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        String result = productService.delete(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Этот роут находит продукт по ID ")
    @GetMapping("/{id}")

    public ProductDTO findById(@PathVariable Long id) throws NullPointerException{
        try{
            return productService.findById(id);

        }catch (NullPointerException exceptione){
            System.out.println(exceptione.getMessage());
            return new ProductDTO();
        }
    }


}
