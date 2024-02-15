package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    Optional<Products> findProductByRemoveDateIsNullAndId(Long id);
    List<Products> findAllAndBOrderByRemoveDateIsNull();
}
