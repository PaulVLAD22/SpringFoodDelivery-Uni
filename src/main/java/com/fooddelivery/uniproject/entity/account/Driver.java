package com.fooddelivery.uniproject.entity.account;

import com.fooddelivery.uniproject.entity.location.Address;
import com.fooddelivery.uniproject.entity.location.Coordinate;
import com.fooddelivery.uniproject.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="drivers")
public class Driver extends Account {
    @OneToOne
    private Order currentOrder;

    @OneToOne
    private Coordinate coordinate;

    private double salary = 0;

    public Driver(long id, String username, String email, Coordinate coordinate, String password) {
        super(id, username, email, password);
        this.coordinate=coordinate;
    }

    @Override
    public String toString() {
        return "Driver id: " + this.getId() + " " + super.toString();
    }
}