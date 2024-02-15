package com.jtbc.weeklymenu.controller;

import com.jtbc.weeklymenu.dto.UsersDTO;
import com.jtbc.weeklymenu.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователи умпешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Пользователей с ID")
    @GetMapping("/{id}")

    public UsersDTO findById(@PathVariable Long id) throws NullPointerException{
        try{
            return usersService.findById(id);

        }catch (NullPointerException exceptione){
            System.out.println(exceptione.getMessage());
            return new UsersDTO();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все пользователи умпешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает Всех Пользователей ")
    @GetMapping("/all")
    public List<UsersDTO> getAllUser() throws Exception{
        return usersService.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователи умпешно получены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для Create Users")
    @PostMapping("/createUsers")
    public ResponseEntity<Long> createUser(@RequestBody UsersDTO userDTO) throws NullPointerException {
        try {
            Long savedId = usersService.create(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователи умпешно удалены",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для Delete Users")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        String result = usersService.delete(id);
        return ResponseEntity.ok(result);


    }


}
