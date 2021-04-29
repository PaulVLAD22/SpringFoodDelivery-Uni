package com.fooddelivery.uniproject.dtos;

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

    private String name;

    private String email;

    private Order currentOrder;

    private double salary;

    private int coordinateX;

    private int coordinateY;
}
