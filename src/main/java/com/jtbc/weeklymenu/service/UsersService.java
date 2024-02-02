package com.jtbc.weeklymenu.service;

import com.jtbc.weeklymenu.entity.Menu;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepo usersRepo;

    public Users saveUsers(Users users) {
        return usersRepo.save(users);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public Users getUsersById(Long userid) {
        return usersRepo.findById(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users updateUsers(Users updateUsers) {
        Optional<Users> userOptional = usersRepo.findById(updateUsers.getId());
        if (userOptional.isPresent()) {
            Users existingUsers = userOptional.get();
            existingUsers.setUserName(updateUsers.getUserName());
            existingUsers.setPassword(updateUsers.getPassword());
            return usersRepo.save(existingUsers);
        } else {
            throw new RuntimeException("User not found");
        }


    }
    public void deleteUsersById(Long userId) {
        usersRepo.deleteById(userId);
    }






}
