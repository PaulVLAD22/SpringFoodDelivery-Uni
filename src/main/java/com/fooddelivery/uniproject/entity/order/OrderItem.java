package com.fooddelivery.uniproject.entity.order;


import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.local.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // ASTA E O PROBLEMA , AR TREBUI SA REUSESC SA IAU PRODUCTS DIN produsele localului ales, pot face eu manual
    // select from local where ...
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    private long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && product.equals(orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}



