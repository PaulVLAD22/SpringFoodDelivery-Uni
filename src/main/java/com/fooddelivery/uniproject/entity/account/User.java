package com.fooddelivery.uniproject.entity.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends Account {
// add coordinate
    public User(int id, String username, String email, String password) {
        super(id, username, email, password);
    }

    @Override
    public String toString() {
        return "User id: " + this.getId() + " " + super.toString();
    }
}
