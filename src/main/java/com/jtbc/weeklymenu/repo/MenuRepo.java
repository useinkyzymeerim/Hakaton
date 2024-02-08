package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {

    @Query("SELECT m.id AS menuId, m.nameOfMenu, r.id AS recipeId, r.nameOfFood\n" +
            "FROM Menu m\n" +
            "LEFT JOIN m.recipes r\n" +
            "WHERE m.id = :menuId")
    Menu findMenuWithRecipes(@Param("menuId") Long menuId);


}
