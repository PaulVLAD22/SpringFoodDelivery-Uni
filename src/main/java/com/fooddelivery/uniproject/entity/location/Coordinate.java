package com.fooddelivery.uniproject.entity.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="coordinates")
public class Coordinate {
    @Id
    @GeneratedValue
    private long id;

    private int x;
    private int y;

}
