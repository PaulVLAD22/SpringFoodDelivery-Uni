package com.fooddelivery.uniproject.entity.audit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table (name = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Timestamp timestamp;

    public Action(String name){
        this.name = name;
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }
}
