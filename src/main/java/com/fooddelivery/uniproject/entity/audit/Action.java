package com.fooddelivery.uniproject.entity.audit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table (name = "actions")
public class Action {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private Timestamp timestamp;

    public Action(String name){
        this.name = name;
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }
}
