package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDto;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
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
    public List<MenuDTO> getAllMenus() {
        return menuService.getAllMenus();
    }


    @GetMapping("getMenuWithRecipe/{menuId}")
    public ResponseEntity<MenuWithRecipeDto> getMenuWithRecipes(@PathVariable Long menuId) {
        List<MenuWithRecipeDto> menuWithRecipesDTOList = menuService.getMenuWithRecipes(menuId);
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

    @PostMapping("/createMenu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu createdMenu = menuService.create(menu);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Menu> updateMenu(@RequestParam("id") Long id, @RequestBody Menu updatedMenu) {
        Menu existingMenu = menuService.findById(id);

        if (existingMenu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        existingMenu.setNameOfMenu(updatedMenu.getNameOfMenu());


        Menu updatedMenuEntity = menuService.update(existingMenu);
        return new ResponseEntity<>(updatedMenuEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id) {
        Menu existingMenu = menuService.findById(id);

        if (existingMenu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        menuService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

