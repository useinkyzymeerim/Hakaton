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
    // Найти все записи, связанные с конкретным рецептом
    List<RecipesWithProducts> findByRecipe(Recipes recipe);

    // Найти все записи, связанные с конкретным продуктом
    List<RecipesWithProducts> findByProduct(Products product);

    List<RecipesWithProducts> findByRecipe_Menu_Id(Long menuId);
    // Найти общее количество продуктов для конкретного рецепта
    @Query("SELECT SUM(rwp.quantityOfProduct) FROM RecipesWithProducts rwp WHERE rwp.recipe.id = :recipeId")
    Integer getQuantityByRecipeId(@Param("recipeId") Long recipeId);


        List<RecipesWithProducts> findByRecipeId(Long recipeId);

    @Query("SELECT rp FROM RecipesWithProducts rp WHERE rp.recipe.id = :recipeId")
    List<RecipesWithProducts> findRecipeById(@Param("recipeId") Long recipeId);

    @Query("SELECT p.productName FROM RecipesWithProducts rp JOIN rp.product p WHERE rp.recipe.id = :recipeId")
    List<String> findProductNamesByRecipeId(@Param("recipeId") Long recipeId);

    List<RecipesWithProducts> findByRecipe_Id(Long id);
}

