package com.fooddelivery.uniproject.controller;


import com.fooddelivery.uniproject.dto.OrderDto;
import com.fooddelivery.uniproject.dto.RegisterAccountDto;
import com.fooddelivery.uniproject.exception.NoDriverInRangeException;
import com.fooddelivery.uniproject.exception.UserHasNoActiveOrders;
import com.fooddelivery.uniproject.service.UserService;
import com.fooddelivery.uniproject.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//orice endpoint cream va avea in api-ul urmatoarea structura
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    {"email":"email4",
//            "username":"username4",
//            "password":"parola",
//            "coordinate":{
//        "x":20,
//                "y":20
//    }}

    //v1/user/register
    @PostMapping("/register")
    @SneakyThrows
    public ResponseEntity<SuccessDto> registerUser(@RequestBody RegisterAccountDto registerAccountDto) {
        userService.registerUser(registerAccountDto);

        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    // VERIFICA DUPA CE FACI LOCAL SI ADD LOCAL
    @PostMapping("/order")
    @SneakyThrows
    public ResponseEntity<SuccessDto> makeOrder(@RequestBody OrderDto orderDto) {
        try {
            userService.makeOrder(orderDto);
        }
        catch(NoDriverInRangeException e) {
            //nu gasim sofer
            return new ResponseEntity(
                    "No driver in range",
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @PostMapping("/confirm")
    @SneakyThrows
    public ResponseEntity<SuccessDto> confirmOrder(@RequestParam Long userId) {
        try {
            userService.confirmOrder(userId);
        }
        catch(UserHasNoActiveOrders e) {
            return new ResponseEntity(
                    "User has no active orders",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }


}
