package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dto.DriverDto;
import com.fooddelivery.uniproject.dto.RegisterAccountDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.audit.Action;
import com.fooddelivery.uniproject.exception.NoUserWithThisUsername;
import com.fooddelivery.uniproject.exception.NonExistentId;
import com.fooddelivery.uniproject.exception.UsernameOrEmailAlreadyTaken;
import com.fooddelivery.uniproject.repository.ActionRepository;
import com.fooddelivery.uniproject.repository.DriverRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    private DriverRepository driverRepository;
    private ActionRepository actionRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
        this.driverRepository = driverRepository;
    }

    public List<DriverDto> listAll() {

        actionRepository.save(new Action("List all drivers"));

        List<Driver> drivers = driverRepository.findAll();
        List<DriverDto> driverDtos = new ArrayList<>();

        drivers.forEach(driver->driverDtos.add(
                DriverDto.builder()
                        .username(driver.getUsername())
                        .email(driver.getEmail())
                        .salary(driver.getSalary())
                        .currentOrder(driver.getCurrentOrder())
                        .coordinate(driver.getCoordinate())
                        .build())
        );
        return driverDtos;
    }


    @SneakyThrows
    public void registerDriver(RegisterAccountDto registerAccountDto) {

        actionRepository.save(new Action("Register driver"));

        if (driverRepository.findUserByEmailOrUsername(registerAccountDto.getEmail(),registerAccountDto.getUsername()).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }

        Driver driver = Driver.builder()
                .email(registerAccountDto.getEmail())
                .password(registerAccountDto.getPassword())
                .username(registerAccountDto.getUsername())
                .coordinate(registerAccountDto.getCoordinate()).build();

        driverRepository.save(driver);
    }

    @SneakyThrows
    public void removeDriver(Long userId) {

        actionRepository.save(new Action("Remove driver"));

        Driver driver = driverRepository.findById(userId).orElseThrow(() -> new AccountNotFoundException("Driver not found"));

        driverRepository.delete(driver);
    }

    public Driver get(Long id){
        actionRepository.save(new Action("Getting driver by id"));
        return driverRepository.findById(id).orElseThrow(NonExistentId::new);
    }
    public void renameDriver(String oldName, String newName){
        if (driverRepository.findUserByEmailOrUsername("",oldName).isEmpty()) {
            throw new NoUserWithThisUsername();
        }
        if (driverRepository.findUserByEmailOrUsername("",newName).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }
        driverRepository.renameDriver(oldName,newName);

    }

}
