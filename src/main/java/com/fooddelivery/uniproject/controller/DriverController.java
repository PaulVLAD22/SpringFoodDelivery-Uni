package com.fooddelivery.uniproject.controller;

import com.fooddelivery.uniproject.dto.RegisterAccountDto;
import com.fooddelivery.uniproject.service.DriverService;
import com.fooddelivery.uniproject.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // fa sa se afiseze frumos Order-ul

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
    public ResponseEntity<SuccessDto> registerDriver(@RequestBody RegisterAccountDto registerAccountDto) {
        driverService.registerDriver(registerAccountDto);

        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

}
