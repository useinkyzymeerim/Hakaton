package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.RecipesWithProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipesWithProductsRepo extends JpaRepository<RecipesWithProducts,Long> {

    List<RecipesWithProducts> findByRecipe_Menu_Id(Long menuId);
    List<RecipesWithProducts> findByProduct_ProductName(String productName);
}

