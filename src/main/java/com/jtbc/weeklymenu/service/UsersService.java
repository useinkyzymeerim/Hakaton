package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.entity.Users;

import java.util.List;


public interface UsersService {
    UsersDTO findById(Long id);
    List<UsersDTO> findAll() throws Exception;
    Long create(UsersDTO userDTO) throws NullPointerException;
    String delete(Long id);
    Long create(Users users) throws NullPointerException;







}
