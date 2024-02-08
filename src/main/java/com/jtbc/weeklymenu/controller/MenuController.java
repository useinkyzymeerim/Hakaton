package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.service.MenuService;
import com.jtbc.weeklymenu.service.RecipesService;
import com.jtbc.weeklymenu.service.RecipesWithProductsService;
import com.jtbc.weeklymenu.service.impl.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> getAllMenu() {
        return menuService.findAll();
    }

    @GetMapping("/menus/{id}")
    public Menu getByMenuId(@PathVariable Long id) {
        return menuService.findById(id);
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
    @GetMapping("getMenuWithRecipe/{menuId}")
    public Menu getMenuWithRecipes(@PathVariable Long menuId) {
        return menuService.findMenuWithRecipes(menuId);
    }
}
