package com.fooddelivery.uniproject.controller;

import com.fooddelivery.uniproject.dtos.DriverDto;
import com.fooddelivery.uniproject.dtos.RegisterDto;
import com.fooddelivery.uniproject.dtos.UserDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.service.DriverService;
import com.fooddelivery.uniproject.service.UserService;
import com.fooddelivery.uniproject.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // fa sa se afiseze frumos Order-ul

    //{"email":"email1",
    //"userName":"username1",
    //"password":"parola",
    //"coordinateX":20,
    //"coordinateY":20}

    //v1/user/register
    @PostMapping("/register/")
    @SneakyThrows
    public ResponseEntity<SuccessDto> registerUser(@RequestBody RegisterDto registerDto) {
        driverService.registerDriver(registerDto);

        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/")
    @SneakyThrows
    public ResponseEntity<SuccessDto> deleteUser(@RequestParam Long driverId) {
        driverService.removeDriver(driverId);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<DriverDto> get(@PathVariable Long id){
        try {
            Driver driver = driverService.get(id);

            DriverDto driverDto = DriverDto.builder()
                    .name(driver.getUsername())
                    .email(driver.getEmail())
                    .currentOrder(driver.getCurrentOrder())
                    .salary(driver.getSalary())
                    .coordinateX(driver.getCoordinate().getX())
                    .coordinateY(driver.getCoordinate().getY()).build();

            return new ResponseEntity<DriverDto>(driverDto, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<DriverDto>(HttpStatus.NOT_FOUND);
        }
    }
}
