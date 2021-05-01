package com.fooddelivery.uniproject.dto;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountDto {

    @NotNull
    private String email;

    private String username;

    private String password;

    private Coordinate coordinate;

}