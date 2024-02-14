package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    @GetMapping("/users")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> userDTOs = usersService.findAll();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/user/{id}")
    public Users getByUsersId(@PathVariable Long id) {
        return usersService.findById(id);
    }


}
