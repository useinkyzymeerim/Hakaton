package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDTO;
import com.jtbc.weeklymenu.dto.RecipesDTO;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.MenuRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepo menuRepo;
    private final RecipesWithProductsRepo recipesWithProductsRepo;







     @Override
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
    public List<MenuDTO> findAll() throws Exception {
        return null;
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        return null;
    }

    @Override
    public List<MenuWithRecipeDTO> getMenuWithRecipes(Long menuId) {
        List<MenuWithRecipeDTO> menuWithRecipesDTOList = new ArrayList<>();

        Optional<Menu> menuOptional = menuRepo.findById(menuId);
        menuOptional.ifPresent(menu -> {
            MenuWithRecipeDTO menuWithRecipesDTO = new MenuWithRecipeDTO();
            menuWithRecipesDTO.setMenuId(menu.getId());
            menuWithRecipesDTO.setNameOfMenu(menu.getNameOfMenu());

            List<RecipesDTO> recipeDTOList = new ArrayList<>();
            for (Recipes recipe : menu.getRecipes()) {
                RecipesDTO recipeDTO = new RecipesDTO();
                recipeDTO.setRecipeId(recipe.getId());
                recipeDTO.setNameOfFood(recipe.getNameOfFood());
                recipeDTOList.add(recipeDTO);
            }

            menuWithRecipesDTO.setRecipes(recipeDTOList);
            menuWithRecipesDTOList.add(menuWithRecipesDTO);
        });

        return menuWithRecipesDTOList;
    }

    @Override
    public MenuDTO findById(Long id) {
        return null;
    }

}

