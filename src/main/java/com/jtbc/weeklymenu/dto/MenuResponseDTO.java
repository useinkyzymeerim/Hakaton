package com.jtbc.weeklymenu.dto;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Recipes;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MenuResponseDTO {
    private Long id;
    private String nameOfMenu;
    private List<String> recipeNames;
    public static MenuResponseDTO fromMenu(Menu menu) {
        MenuResponseDTO dto = new MenuResponseDTO();
        dto.setId(menu.getId());
        dto.setNameOfMenu(menu.getNameOfMenu());
        dto.setRecipeNames(menu.getRecipes().stream().map(Recipes::getNameOfFood).collect(Collectors.toList()));
        return dto;
    }
}
