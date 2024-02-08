package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getByUsersId(@PathVariable Long id) {
        return usersService.findById(id);
    }
    @PostMapping("/createMenu")
    public ResponseEntity<Users> createUsers(@RequestBody Users users) {
        Users createdUsers = usersService.create(users);
        return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateMenu(@RequestParam("id") Long id, @RequestBody Users updatedUsers) {
        Users existingUsers = usersService.findById(id);

        if (existingUsers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        existingUsers.setUserName(updatedUsers.getUserName());


        Users updatedUsersEntity = usersService.update(existingUsers);
        return new ResponseEntity<>(updatedUsersEntity, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("id") Long id) {
        Users existingUsers = usersService.findById(id);

        if (existingUsers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
