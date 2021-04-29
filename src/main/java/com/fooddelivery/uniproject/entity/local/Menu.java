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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "menu",cascade = CascadeType.REMOVE)
    private List<Product> products;

}

