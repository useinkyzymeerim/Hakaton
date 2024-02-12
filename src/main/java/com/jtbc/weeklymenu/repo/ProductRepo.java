package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    Products findByProductName(String productName);

    Products getProductByProductName(String name);
}
