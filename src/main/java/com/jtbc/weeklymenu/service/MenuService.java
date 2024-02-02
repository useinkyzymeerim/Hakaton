package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.MenuRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
    public class MenuService {
    private final RecipesWithProductsRepo recipesWithProductsRepo;
    private final MenuRepo menuRepo;



    public Menu saveMenu(Menu menu) {
        return menuRepo.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepo.findAll();
    }

    public Menu getMenuById(Long menuId) {
        return menuRepo.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    public Menu updateMenu(Menu updateMenu) {
        Optional<Menu> menuOptional = menuRepo.findById(updateMenu.getId());
        if (menuOptional.isPresent()) {
            Menu existingMenu = menuOptional.get();
            existingMenu.setNameOfMenu(updateMenu.getNameOfMenu());
            return menuRepo.save(existingMenu);
        } else {
            throw  new RuntimeException("Menu not found");
        }

    }



    public void deleteMenuById(Long menuId) {
        menuRepo.deleteById(menuId);
    }


        public Map<Products, Integer> calculateRequiredProductsForMenu(Long menuId) {
            List<RecipesWithProducts> recipesWithProductsList = recipesWithProductsRepo.findByRecipe_Menu_Id(menuId);

            Map<Products, Integer> productQuantityMap = new HashMap<>();

            for (RecipesWithProducts recipesWithProducts : recipesWithProductsList) {
                Products product = recipesWithProducts.getProduct();
                int quantity = recipesWithProducts.getQuantityOfProduct();

                int totalQuantity = productQuantityMap.getOrDefault(product, 0) + quantity;
                productQuantityMap.put(product, totalQuantity);
            }

            return productQuantityMap;
        }

    }


