package com.fooddelivery.uniproject.dto;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.fooddelivery.uniproject.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {

    private String username;

    private String email;

    private Order currentOrder;

    private double salary;

    private Coordinate coordinate;
}
