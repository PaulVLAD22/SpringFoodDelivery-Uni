package com.fooddelivery.uniproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

   private String name;

   private String email;

   private int coordinateX;

   private int coordinateY;
}
