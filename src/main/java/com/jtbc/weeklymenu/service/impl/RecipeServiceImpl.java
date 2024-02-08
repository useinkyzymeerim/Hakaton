package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.service.RecipesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipesService {
    private final RecipesRepo recipesRepo;
    @Override
    public Recipes create(Recipes recipes) {
        Recipes recipes1 = new Recipes();

        return recipesRepo.save(recipes);
    }

    @Override
    public Recipes findById(Long id) {
        return recipesRepo.findById(id).orElse(null);
    }

    @Override
    public Recipes update(Recipes recipes) {
        Optional<Recipes> recipesOptional = recipesRepo.findById(recipes.getId());
        if (recipesOptional.isPresent()) {
            Recipes existingRecipes = recipesOptional.get();
            existingRecipes.setNameOfFood(recipes.getNameOfFood());
            return recipesRepo.save(existingRecipes);
        } else {
            throw new RuntimeException("Menu not found");
        }
    }

    @Override
    public void delete (Long id){
        Recipes existingRecipes = recipesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipes not found with id: " + id));

        recipesRepo.delete(existingRecipes);
    }


    @Override
    public List<Recipes> findAll () {
        return recipesRepo.findAll();
    }
}
