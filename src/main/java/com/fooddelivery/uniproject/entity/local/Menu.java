package com.fooddelivery.uniproject.entity.local;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table (name="menu")
public class Menu {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(mappedBy = "menu")
    private List<Product> products;

}

