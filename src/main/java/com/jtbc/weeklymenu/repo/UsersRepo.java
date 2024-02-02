package com.jtbc.weeklymenu.repo;

import com.jtbc.weeklymenu.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
}
