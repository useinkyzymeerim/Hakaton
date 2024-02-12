package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDto;
import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;

import java.util.List;
import java.util.Map;


public interface MenuService {
        Menu create(Menu menu);
        List<Menu> findAll();
        List<MenuDTO> getAllMenus();


        public Menu getMenu(Long menuId);


        Menu update(Menu menu);


        void delete(Long menuId);
        public Map<Products, Integer> calculateRequiredProductsForMenu (Long id);




    List<MenuWithRecipeDto> getMenuWithRecipes(Long menuId);

        Menu findById(Long id);
}