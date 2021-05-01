package com.fooddelivery.uniproject.dto;

import com.fooddelivery.uniproject.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private long userId;

    private long localId;

//    {"persons":[{"name":"shail1","age":"2"},{"name":"shail2","age":"3"}]}
    private List<OrderItem> orderItems;
}
