package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.dtos.RegisterDto;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.fooddelivery.uniproject.exception.UsernameOrEmailAlreadyTaken;
import com.fooddelivery.uniproject.repository.CoordinateRepository;
import com.fooddelivery.uniproject.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class UserService {

    private UserRepository userRepository;
    private CoordinateRepository coordinateRepository;

    @Autowired
    public UserService(UserRepository userRepository, CoordinateRepository coordinateRepository) {
        this.userRepository = userRepository;
        this.coordinateRepository= coordinateRepository;
    }

    @SneakyThrows
    public void registerUser(RegisterDto registerDto) {
        if (userRepository.findUserByEmail(registerDto.getEmail()).isPresent()) {
            throw new UsernameOrEmailAlreadyTaken();
        }

        Coordinate coordinate = Coordinate.builder()
                .x(registerDto.getCoordinateX())
                .y(registerDto.getCoordinateY()).build();

        coordinateRepository.save(coordinate);

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .username(registerDto.getUserName())
                .coordinate(coordinate).build();

        userRepository.save(user);
    }

    @SneakyThrows
    public void removeUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AccountNotFoundException("Driver not found"));

        userRepository.delete(user);
    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }


//    @SneakyThrows
//    public UserDto getUser(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        UserDto userDto = UserDto.builder()
//                .email(user.getEmail())
//                .firstName(user.getFirstName())
//                .name(user.getUsername()).build();
//
//        return userDto;
//    }
}
