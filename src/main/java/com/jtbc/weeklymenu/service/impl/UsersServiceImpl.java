package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.repo.UsersRepo;
import com.jtbc.weeklymenu.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;


    @Override
    public Users findById(Long id) {
        return usersRepo.findById(id).orElse(null);
    }

    @Override
    public List<UsersDTO> findAll () {
        List<Users> users = usersRepo.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private UsersDTO convertToDTO (Users users){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsersId(users.getId());
        usersDTO.setUsersName(users.getUserName());
        return usersDTO;
    }
}
