package com.fooddelivery.uniproject.controller;


import com.fooddelivery.uniproject.dtos.RegisterDto;
import com.fooddelivery.uniproject.dtos.UserDto;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.service.UserService;
import com.fooddelivery.uniproject.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
//orice endpoint cream va avea in api-ul urmatoarea structura
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // fa comanda /order



    //{"email":"email1",
    //"userName":"username1",
    //"password":"parola",
    //"coordinateX":20,
    //"coordinateY":20}

    //v1/user/register
    @PostMapping("/register/")
    @SneakyThrows
    public ResponseEntity<SuccessDto> registerUser(@RequestBody RegisterDto registerDto) {
        userService.registerUser(registerDto);

        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/")
    @SneakyThrows
    public ResponseEntity<SuccessDto> deleteUser(@RequestParam Long userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id){
        try {
            User user = userService.get(id);

            UserDto userDto = UserDto.builder()
                    .name(user.getUsername())
                    .email(user.getEmail())
                    .coordinateX(user.getCoordinate().getX())
                    .coordinateY(user.getCoordinate().getY()).build();

            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

}
