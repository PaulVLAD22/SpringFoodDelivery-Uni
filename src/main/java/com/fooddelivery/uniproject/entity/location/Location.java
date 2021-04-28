package com.fooddelivery.uniproject.entity.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Address address;
    @OneToOne
    private Coordinate coordinate;
}
