package com.jtbc.weeklymenu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeWithProductsDTO {
    private Long recipeId;
    private String recipeName;
    private List<ProductWithQuantityDTO> products;
}
