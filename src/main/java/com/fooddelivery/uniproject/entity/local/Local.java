package com.fooddelivery.uniproject.entity.local;

import com.fooddelivery.uniproject.entity.location.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="locals")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Menu menu;
    @OneToOne
    private Location location;

}
