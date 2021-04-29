package com.fooddelivery.uniproject.entity.account;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends Account {

    @OneToOne(cascade = CascadeType.REMOVE)
    private Coordinate coordinate;

    public User(long id, String username, String email, String password,Coordinate coordinate) {
        super(id, username, email, password);
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "User id: " + this.getId() + " " + super.toString();
    }
}
