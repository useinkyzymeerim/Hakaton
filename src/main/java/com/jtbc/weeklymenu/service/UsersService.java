package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsersService {
    Users create(Users user);
    Users findById(Long id);
    Users update(Users user);
    void delete(Long id);
    List<Users> findAll();





}
