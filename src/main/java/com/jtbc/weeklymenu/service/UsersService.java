package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.entity.Users;

import java.util.List;


public interface UsersService {
    Users findById(Long id);
    List<UsersDTO> findAll();






}
