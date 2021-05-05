package com.fooddelivery.uniproject.controller;

import com.fooddelivery.uniproject.dto.DriverDto;
import com.fooddelivery.uniproject.dto.RegisterLocalDto;
import com.fooddelivery.uniproject.dto.UserDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Product;
import com.fooddelivery.uniproject.entity.order.Order;
import com.fooddelivery.uniproject.exception.LocalNameAlreadyTakenException;
import com.fooddelivery.uniproject.exception.NoUserWithThisUsername;
import com.fooddelivery.uniproject.exception.NonExistentId;
import com.fooddelivery.uniproject.exception.UsernameOrEmailAlreadyTaken;
import com.fooddelivery.uniproject.service.DriverService;
import com.fooddelivery.uniproject.service.LocalService;
import com.fooddelivery.uniproject.service.OrderService;
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
    private OrderService orderService;

    @Autowired
    public AdminController(DriverService driverService, UserService userService, LocalService localService,
                           OrderService orderService) {
        this.driverService = driverService;
        this.userService = userService;
        this.localService = localService;
        this.orderService = orderService;
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
            return new ResponseEntity<DriverDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/renameDriver")
    public ResponseEntity renameDriver(@RequestParam String oldName, @RequestParam String newName){
        try {
            driverService.renameDriver(oldName, newName);
            return new ResponseEntity(new SuccessDto(), HttpStatus.OK);
        }catch (NoUserWithThisUsername e) {
            return new ResponseEntity(
                    "There is no driver with this username",
                    HttpStatus.NO_CONTENT);
        }
        catch (UsernameOrEmailAlreadyTaken e){
            return new ResponseEntity("Username taken",HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/renameUser")
    public ResponseEntity renameUser(@RequestParam String oldName, @RequestParam String newName){
        try {
            userService.renameUser(oldName, newName);
            return new ResponseEntity(new SuccessDto(), HttpStatus.OK);
        }catch (NoUserWithThisUsername e) {
            return new ResponseEntity(
                    "There is no user with this username",
                    HttpStatus.NO_CONTENT);
        }
        catch (UsernameOrEmailAlreadyTaken e){
            return new ResponseEntity("Username taken",HttpStatus.BAD_REQUEST);
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
        try {
            localService.registerLocal(registerLocalDto);
            return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
        }
        catch(LocalNameAlreadyTakenException e){
            return new ResponseEntity("Local name taken", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("local/id={id}/rename")
    @SneakyThrows
    public ResponseEntity<Local> renameLocal (@PathVariable Long id, @RequestParam String name){
        try {
            //localhost:8080/admin/local/id=1?name="numenou"
            localService.rename(id,name);
            return new ResponseEntity<Local>(HttpStatus.OK);

        } catch (NonExistentId e) {
            return new ResponseEntity("No local with this id",HttpStatus.BAD_REQUEST);
        }
        catch (LocalNameAlreadyTakenException e){
            return new ResponseEntity("Name already taken",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("local/id={id}/addProduct")
    @SneakyThrows
    @ResponseBody
    public ResponseEntity<Local> getLocal(@PathVariable Long id, @RequestBody Product product) {
        try {
//            {"name":"nume",
//                    "price":50}
            localService.addProduct(id,product);
            return new ResponseEntity<Local>(HttpStatus.OK);

        } catch (NonExistentId e) {
            return new ResponseEntity<Local>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("locals")
    public List<Local> getLocals() {
        return localService.listAll();
    }

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderService.listAll();
    }
}
