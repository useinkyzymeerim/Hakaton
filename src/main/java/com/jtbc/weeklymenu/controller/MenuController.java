package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDTO;
import com.jtbc.weeklymenu.dto.ResponseMessageAPI;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.enums.ResultCode;
import com.jtbc.weeklymenu.enums.ResultCodeAPI;
import com.jtbc.weeklymenu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/getAllMenus")
    public ResponseMessageAPI<List<MenuDTO>> getAllMenus() {
        try {
            return new ResponseMessageAPI<>(
                    menuService.getAllMenus(),
                    ResultCodeAPI.SUCCESS,
                    "SUCCESS",
                    "SUCCESS",
                    ResultCode.OK.getHttpCode()
            );
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        }

    }
    @GetMapping("getMenuWithRecipe/{menuId}")
    public ResponseEntity<MenuWithRecipeDTO>getMenuWithRecipes(@PathVariable Long menuId) {
        List<MenuWithRecipeDTO> menuWithRecipesDTOList = menuService.getMenuWithRecipes(menuId);
        if (menuWithRecipesDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menuWithRecipesDTOList.get(0));
    }
    @GetMapping("/{menuId}/requiredProducts")
     public ResponseEntity<?> getRequiredProductsForMenu(@PathVariable Long menuId) {
        try {
            Map<Products, Integer> productQuantityMap = menuService.calculateRequiredProductsForMenu(menuId);

            // Преобразование Map<Products, Integer> в Map<String, Integer> для удобства сериализации в JSON
            Map<String, Integer> productQuantityStringMap = new HashMap<>();
            for (Map.Entry<Products, Integer> entry : productQuantityMap.entrySet()) {
                productQuantityStringMap.put(entry.getKey().getProductName(), entry.getValue());
            }

            return new ResponseEntity<>(productQuantityStringMap, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to calculate required products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

