package com.fooddelivery.uniproject.dto;

import com.fooddelivery.uniproject.entity.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLocalDto {

    private String name;

    private Location location;
}
