package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dtos.RegisterDto;
import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.fooddelivery.uniproject.exception.UsernameOrEmailAlreadyTaken;
import com.fooddelivery.uniproject.repository.CoordinateRepository;
import com.fooddelivery.uniproject.repository.DriverRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class DriverService {

    private CoordinateRepository coordinateRepository;
    private DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository, CoordinateRepository coordinateRepository) {
        this.driverRepository = driverRepository;
        this.coordinateRepository= coordinateRepository;
    }

    @SneakyThrows
    public void registerDriver(RegisterDto registerDto) {
        if (driverRepository.findUserByEmail(registerDto.getEmail()).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }
        Coordinate coordinate = Coordinate.builder()
                .x(registerDto.getCoordinateX())
                .y(registerDto.getCoordinateY()).build();

        coordinateRepository.save(coordinate);

        Driver driver = Driver.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .username(registerDto.getUserName())
                .coordinate(coordinate).build();

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
