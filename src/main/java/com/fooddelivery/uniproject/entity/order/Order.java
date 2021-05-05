package com.fooddelivery.uniproject.entity.order;

import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Product;
import com.fooddelivery.uniproject.entity.location.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private OrderStatus status;

    @ManyToOne
    private User user;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Local local;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;
}
