package com.jtbc.weeklymenu.dto;

import lombok.Data;

import java.util.List;
@Data
public class MenuWithRecipeDto {
    private Long menuId;
    private String nameOfMenu;
    private List<RecipesDto> recipes;
}
