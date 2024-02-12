package com.jtbc.weeklymenu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesDto {
    private Long recipeId;
    private String nameOfFood;
}
