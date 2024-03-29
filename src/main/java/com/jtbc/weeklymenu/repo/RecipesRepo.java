package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.dto.RecipeDetailsDTO;
import com.jtbc.weeklymenu.entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipesRepo extends JpaRepository<Recipes,Long> {
    Recipes findByNameOfFood(String nameOfFood);
    @Query("SELECT new com.jtbc.weeklymenu.dto.RecipeDetailsDTO( r.nameOfFood,  p.productName, rp.quantityOfProduct) " +
                "FROM Recipes r " +
                "JOIN r.recipesWithProducts rp " +
                "JOIN rp.product p " +
                "WHERE r.id = :recipeId")
    List<RecipeDetailsDTO> findRecipeDetails(@Param("recipeId") Long recipeId);
    List<Recipes> findByNameOfFoodIgnoreCase(String recipeName);

    Optional<Recipes> findRecipesByRemoveDateIsNullAndId(Long id);
    List<Recipes> findAllAndBOrderByRemoveDateIsNull();
}




