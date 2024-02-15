package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {

    Optional<Menu> findMenuByRemoveDateIsNullAndId(Long id);
    List<Menu> findAllAndBOrderByRemoveDateIsNull();

}
