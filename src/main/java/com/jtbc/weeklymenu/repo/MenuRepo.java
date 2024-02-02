package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {
    Menu findByNameOfMenu(String nameOfMenu);

}
