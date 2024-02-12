package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDto;
import com.jtbc.weeklymenu.dto.RecipesDto;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import com.jtbc.weeklymenu.repo.MenuRepo;
import com.jtbc.weeklymenu.repo.RecipesWithProductsRepo;
import com.jtbc.weeklymenu.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


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
    public List<Menu> findAll() {
        return null;
    }

    @Override
    public Menu getMenu(Long menuId) {
        return menuRepo.findById(menuId).get();
    }
    @Override
    public List<MenuDTO> getAllMenus() {
        List<Menu> menus = menuRepo.findAll();
        return menus.stream().map(menu -> {
            MenuDTO dto = new MenuDTO();
            dto.setId(menu.getId());
            dto.setNameOfMenu(menu.getNameOfMenu());
            return dto;
        }).collect(Collectors.toList());

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
    public List<MenuWithRecipeDto> getMenuWithRecipes(Long menuId) {
        List<MenuWithRecipeDto> menuWithRecipesDTOList = new ArrayList<>();

        Optional<Menu> menuOptional = menuRepo.findById(menuId);
        menuOptional.ifPresent(menu -> {
            MenuWithRecipeDto menuWithRecipesDTO = new MenuWithRecipeDto();
            menuWithRecipesDTO.setMenuId(menu.getId());
            menuWithRecipesDTO.setNameOfMenu(menu.getNameOfMenu());

            List<RecipesDto> recipeDTOList = new ArrayList<>();
            for (Recipes recipe : menu.getRecipes()) {
                RecipesDto recipeDTO = new RecipesDto();
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
    public Menu findById(Long id) {
        return null;
    }
}

