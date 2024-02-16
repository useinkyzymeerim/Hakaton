package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.MenuDTO;
import com.jtbc.weeklymenu.dto.MenuWithRecipeDTO;
import com.jtbc.weeklymenu.entity.Products;
import com.jtbc.weeklymenu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "WeeklyMenu", description = "Тут находятся все роуты связанные для работы с меню")
@RequiredArgsConstructor
@RestController
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает весь список Меню")
    @GetMapping("/getAllMenus")
    public List<MenuDTO> getAllMenu() throws Exception{
        return menuService.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Меню с рецептами получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Меню по айди с рецептами")
    @GetMapping("getMenuWithRecipe/{menuId}")
    public ResponseEntity<MenuWithRecipeDTO>getMenuWithRecipes(@PathVariable Long menuId) {
        List<MenuWithRecipeDTO> menuWithRecipesDTOList = menuService.getMenuWithRecipes(menuId);
        if (menuWithRecipesDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menuWithRecipesDTOList.get(0));
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Количество продуктов получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает количество всех продуктов в одном меню по айди")
    @GetMapping("/{menuId}/requiredProducts")
     public ResponseEntity<?> getRequiredProductsForMenu(@PathVariable Long menuId) {
        try {
            Map<Products, Integer> productQuantityMap = menuService.calculateRequiredProductsForMenu(menuId);


            Map<String, Integer> productQuantityStringMap = new HashMap<>();
            for (Map.Entry<Products, Integer> entry : productQuantityMap.entrySet()) {
                productQuantityStringMap.put(entry.getKey().getProductName(), entry.getValue());
            }

            return new ResponseEntity<>(productQuantityStringMap, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to calculate required products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Меню успешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для create Menu")
    @PostMapping("/createMenu")
    public ResponseEntity<Long> createMenu(@RequestBody MenuDTO menuDTO) throws NullPointerException {
        try {
            Long savedId = menuService.create(menuDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = " Меню успешно удалено",
                    content = {@Content(mediaType = "application/json")})
    })

    @Operation(summary = "Этот роут для delete Menu")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        String result = menuService.delete(id);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Этот роут возвращает Меню с ID")
    @GetMapping("/{id}")

    public MenuDTO findById(@PathVariable Long id) throws NullPointerException{
        try{
            return menuService.findById(id);

        }catch (NullPointerException exception){
            System.out.println(exception.getMessage());
            return new MenuDTO();
        }
    }





}

