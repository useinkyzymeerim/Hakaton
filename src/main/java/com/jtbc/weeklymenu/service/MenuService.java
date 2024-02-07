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


    public interface MenuService {
        Menu create(Menu menu);
        List<Menu> findAll();


        Menu findById(Long id);


        Menu update(Menu menu);


        void delete(Long menuId);
        public Map<Products, Integer> calculateRequiredProductsForMenu (Long id);




}