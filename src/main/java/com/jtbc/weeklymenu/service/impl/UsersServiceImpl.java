package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.entity.Recipes;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.repo.RecipesRepo;
import com.jtbc.weeklymenu.repo.UsersRepo;
import com.jtbc.weeklymenu.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;
    @Override
    public Users create(Users users) {
        return usersRepo.save(users);
    }

    @Override
    public Users findById(Long id) {
        return usersRepo.findById(id).orElse(null);
    }

    @Override
    public Users update(Users users) {
        Optional<Users> usersOptional = usersRepo.findById(users.getId());
        if (usersOptional.isPresent()) {
            Users existingUsers = usersOptional.get();
            existingUsers.setUserName(users.getUserName());
            return usersRepo.save(existingUsers);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void delete (Long id){
        Users existingUsers = usersRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        usersRepo.delete(existingUsers);
    }


    @Override
    public List<Users> findAll () {
        return usersRepo.findAll();
    }
}
