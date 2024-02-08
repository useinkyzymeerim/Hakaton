package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.MenuRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepo menuRepo;
    private final RecipesWithProductsRepo recipesWithProductsRepo;

    @Override
    public Menu create(Menu menu) {
        return menuRepo.save(menu);
    }

    @Override
    public Menu findById(Long id) {
        return menuRepo.findById(id).orElse(null);
    }

    @Override
    public Menu update(Menu menu) {
        Optional<Menu> menuOptional = menuRepo.findById(menu.getId());
        if (menuOptional.isPresent()) {
            Menu existingMenu = menuOptional.get();
            existingMenu.setNameOfMenu(menu.getNameOfMenu());
            return menuRepo.save(existingMenu);
        } else {
            throw new RuntimeException("Menu not found");
        }
    }

        @Override
        public void delete (Long id){
            Menu existingMenu = menuRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Menu not found with id: " + id));

            menuRepo.delete(existingMenu);
        }


        @Override
        public List<Menu> findAll () {
            return menuRepo.findAll();
        }
        public Map<Products, Integer> calculateRequiredProductsForMenu (Long id){
            List<RecipesWithProducts> recipesWithProductsList = recipesWithProductsRepo.findByRecipe_Menu_Id(id);

            Map<Products, Integer> productQuantityMap = new HashMap<>();

            for (RecipesWithProducts recipesWithProducts : recipesWithProductsList) {
                Products product = recipesWithProducts.getProduct();
                int quantity = recipesWithProducts.getQuantityOfProduct();

                int totalQuantity = productQuantityMap.getOrDefault(product, 0) + quantity;
                productQuantityMap.put(product, totalQuantity);
            }

            return productQuantityMap;
        }

    @Override
    public Menu findMenuWithRecipes(Long menuId) {
        return menuRepo.findMenuWithRecipes(menuId);
    }

    }

