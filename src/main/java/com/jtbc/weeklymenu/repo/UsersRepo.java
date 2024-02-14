package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.recipes WHERE u.id = :userId")
    Optional<User> findByIdWithRecipes(@Param("userId") Long userId);
}
