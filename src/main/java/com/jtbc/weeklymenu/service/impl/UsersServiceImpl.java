package com.jtbc.weeklymenu.service.impl;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.entity.Users;
import com.jtbc.weeklymenu.repo.UsersRepo;
import com.jtbc.weeklymenu.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;



    @Override
    public Long create(UsersDTO userDTO) throws NullPointerException {
        if (userDTO.getId() == null) {
            Users users = new Users();
            users.setUserName(userDTO.getUsersName());
            users = usersRepo.save(users);
            return users.getId();
        } else {
            return update(userDTO);
        }
    }

    private Long update(UsersDTO userDTO) throws NullPointerException {
        Optional<Users> optionalUsers = usersRepo.findById(userDTO.getId());

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setUserName(userDTO.getUsersName());
            return usersRepo.save(users).getId();

        } else throw new NullPointerException(String.format("Пользователь с id %s не найдена", userDTO.getId()));
    }

    public String delete(Long id) {
        Optional<Users> optionalUsers = usersRepo.findById(id);

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            users.setRemoveDate(new Date(System.currentTimeMillis()));
            usersRepo.save(users);

        } else throw new NullPointerException(String.format("Пользователь с id %s не найдена", id));
        return "Deleted";
    }

    public List<UsersDTO> findAll() throws Exception {
        var list = usersRepo.findAllAndBOrderByRemoveDateIsNull();

        List<UsersDTO> dtoList = new ArrayList<>();

        for (Users users : list) {
            var dto = new UsersDTO(
                    users.getId(),
                    users.getUserName()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }

    public UsersDTO findById(Long id) {
        Optional<Users> users = usersRepo.findUserByRemoveDateIsNullAndId(id);

        var dto = new UsersDTO();
        if (users.isPresent()) {
            dto = new UsersDTO(
                    users.get().getId(),
                    users.get().getUserName()
            );
        } else throw new NullPointerException(String.format("Пользователь с id %s не найдена", id));
        return dto;
    }
}
