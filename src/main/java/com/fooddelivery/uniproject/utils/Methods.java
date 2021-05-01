package com.fooddelivery.uniproject.utils;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Methods {
    public double calculateDistance(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) +
                Math.pow(c2.getY() - c1.getY(), 2));
    }
}
