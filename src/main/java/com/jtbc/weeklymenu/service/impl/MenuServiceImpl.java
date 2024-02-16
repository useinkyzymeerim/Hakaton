package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDTO;
import com.jtbc.weeklymenu.dto.RecipesDto;
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


@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepo menuRepo;
    private final RecipesWithProductsRepo recipesWithProductsRepo;

    @Override
    public Long create(MenuDTO dto) throws NullPointerException {
        if (dto.getId() == null) {
            Menu menu = new Menu();
            menu.setNameOfMenu(dto.getNameOfMenu());
            menu = menuRepo.save(menu);
            return menu.getId();
        } else {
            return update(dto);
        }
    }
    private Long update(MenuDTO dto) throws NullPointerException {
        Optional<Menu> optionalMenu = menuRepo.findById(dto.getId());

        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            menu.setNameOfMenu(dto.getNameOfMenu());
            return menuRepo.save(menu).getId();

        } else throw new NullPointerException(String.format("Меню с id %s не найдена", dto.getId()));
    }
    public String delete(Long id) {
        Optional<Menu> optionalMenu = menuRepo.findById(id);

        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            menu.setRemoveDate(new Date(System.currentTimeMillis()));
            menuRepo.save(menu);

        } else throw new NullPointerException(String.format("Меню с id %s не найдена", id));
        return "Deleted";
    }

    public MenuDTO findById(Long id) {
        Optional<Menu> menu = menuRepo.findMenuByRemoveDateIsNullAndId(id);

        var dto = new MenuDTO();
        if (menu.isPresent()) {
            dto = new MenuDTO(
                    menu.get().getId(),
                    menu.get().getNameOfMenu()
            );
        } else throw new NullPointerException(String.format("Меню с id %s не найдена", id));
        return dto;
    }
    public List<MenuDTO> findAll() throws Exception {
        var list = menuRepo.findAllAndBOrderByRemoveDateIsNull();

        List<MenuDTO> dtoList = new ArrayList<>();

        for (Menu menu : list) {
            var dto = new MenuDTO(
                    menu.getId(),
                    menu.getNameOfMenu()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }





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
    public List<MenuWithRecipeDTO> getMenuWithRecipes(Long menuId) {
        List<MenuWithRecipeDTO> menuWithRecipesDTOList = new ArrayList<>();

        Optional<Menu> menuOptional = menuRepo.findById(menuId);
        menuOptional.ifPresent(menu -> {
            MenuWithRecipeDTO menuWithRecipesDTO = new MenuWithRecipeDTO();
            menuWithRecipesDTO.setMenuId(menu.getId());
            menuWithRecipesDTO.setNameOfMenu(menu.getNameOfMenu());

            List<RecipesDto> recipeDTOList = new ArrayList<>();
            for (Recipes recipe : menu.getRecipes()) {
                RecipesDto recipeDTO = new RecipesDto();
                recipeDTO.setId(recipe.getId());
                recipeDTO.setNameOfFood(recipe.getNameOfFood());
                recipeDTOList.add(recipeDTO);
            }

            menuWithRecipesDTO.setRecipes(recipeDTOList);
            menuWithRecipesDTOList.add(menuWithRecipesDTO);
        });

        return menuWithRecipesDTOList;
    }







}

