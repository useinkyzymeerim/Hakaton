package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDTO;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;

import java.util.List;
import java.util.Map;


public interface MenuService {

        List<MenuDTO> findAll() throws Exception;
        List<MenuDTO> getAllMenus();
        List<MenuWithRecipeDTO> getMenuWithRecipes(Long menuId);

        MenuDTO findById(Long id);
         Map<Products, Integer> calculateRequiredProductsForMenu (Long id);

}