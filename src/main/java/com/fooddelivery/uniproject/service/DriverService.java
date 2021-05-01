package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dto.DriverDto;
import com.fooddelivery.uniproject.dto.RegisterAccountDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.exception.UsernameOrEmailAlreadyTaken;
import com.fooddelivery.uniproject.repository.CoordinateRepository;
import com.fooddelivery.uniproject.repository.DriverRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    private CoordinateRepository coordinateRepository;
    private DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, CoordinateRepository coordinateRepository) {
        this.driverRepository = driverRepository;
        this.coordinateRepository= coordinateRepository;
    }

    public List<DriverDto> listAll() {
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
        if (driverRepository.findUserByEmail(registerAccountDto.getEmail()).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }

        coordinateRepository.save(registerAccountDto.getCoordinate());

        Driver driver = Driver.builder()
                .email(registerAccountDto.getEmail())
                .password(registerAccountDto.getPassword())
                .username(registerAccountDto.getUsername())
                .coordinate(registerAccountDto.getCoordinate()).build();

        driverRepository.save(driver);
    }

    @SneakyThrows
    public void removeDriver(Long userId) {
        Driver driver = driverRepository.findById(userId).orElseThrow(() -> new AccountNotFoundException("Driver not found"));

        driverRepository.delete(driver);
    }

    public Driver get(Long id){
        return driverRepository.findById(id).get();
    }

}
