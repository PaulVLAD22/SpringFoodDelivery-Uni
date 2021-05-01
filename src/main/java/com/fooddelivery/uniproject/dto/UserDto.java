package com.fooddelivery.uniproject.dto;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

   private String username;

   private String email;

   private Coordinate coordinate;

}
