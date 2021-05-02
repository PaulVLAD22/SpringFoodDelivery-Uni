package com.fooddelivery.uniproject.controller;

import com.fooddelivery.uniproject.dto.DriverDto;
import com.fooddelivery.uniproject.dto.RegisterLocalDto;
import com.fooddelivery.uniproject.dto.UserDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Product;
import com.fooddelivery.uniproject.exception.NonExistentId;
import com.fooddelivery.uniproject.service.DriverService;
import com.fooddelivery.uniproject.service.LocalService;
import com.fooddelivery.uniproject.service.UserService;
import com.fooddelivery.uniproject.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private DriverService driverService;
    private UserService userService;
    private LocalService localService;

    @Autowired
    public AdminController(DriverService driverService, UserService userService, LocalService localService) {
        this.driverService = driverService;
        this.userService = userService;
        this.localService = localService;
    }


    @DeleteMapping("driver/delete/")
    @SneakyThrows
    public ResponseEntity<SuccessDto> deleteDriver(@RequestParam Long driverId) {
        driverService.removeDriver(driverId);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @GetMapping("driver/id={id}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Long id) {
        try {
            Driver driver = driverService.get(id);

            DriverDto driverDto = DriverDto.builder()
                    .username(driver.getUsername())
                    .email(driver.getEmail())
                    .currentOrder(driver.getCurrentOrder())
                    .salary(driver.getSalary())
                    .coordinate(driver.getCoordinate()).build();

            return new ResponseEntity<DriverDto>(driverDto, HttpStatus.OK);
        } catch (NonExistentId e) {
            return new ResponseEntity<DriverDto>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/drivers")
    public List<DriverDto> getDrivers() {
        return driverService.listAll();
    }


    @DeleteMapping("/user/delete")
    @SneakyThrows
    public ResponseEntity<SuccessDto> deleteUser(@RequestParam Long userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @GetMapping("user/id={id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        try {
            User user = userService.get(id);

            UserDto userDto = UserDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .coordinate(user.getCoordinate()).build();

            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        } catch (NonExistentId e) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.listAll();
    }


    @PostMapping("/local/register")
    @SneakyThrows
    public ResponseEntity<SuccessDto> registerLocal(@RequestBody RegisterLocalDto registerLocalDto) {
//
//        {"name":"local1",
//                "location":{
//            "address":{
//                "country":"romania",
//                        "city":"bucuresti",
//                        "street":"strada"
//            },
//            "coordinate":{
//                "x":30,
//                        "y":40
//            }
//        },
//            "menu":{}}
        localService.registerLocal(registerLocalDto);

        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @PostMapping("local/id={id}/addProduct")
    @SneakyThrows
    @ResponseBody
    public ResponseEntity<Local> getLocal(@PathVariable Long id, @RequestBody Product product) {
        try {
            localService.addProduct(id,product);
            return new ResponseEntity<Local>(HttpStatus.OK);

        } catch (NonExistentId e) {
            return new ResponseEntity<Local>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("locals")
    public List<Local> getLocals() {
        return localService.listAll();
    }
}
