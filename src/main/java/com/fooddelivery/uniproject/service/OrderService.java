package com.fooddelivery.uniproject.service;

import com.fooddelivery.uniproject.entity.audit.Action;
import com.fooddelivery.uniproject.entity.local.Local;
import com.fooddelivery.uniproject.entity.order.Order;
import com.fooddelivery.uniproject.repository.ActionRepository;
import com.fooddelivery.uniproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ActionRepository actionRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ActionRepository actionRepository) {
        this.orderRepository = orderRepository;
        this.actionRepository = actionRepository;
    }

    public List<Order> listAll() {
        actionRepository.save(new Action("List all orders"));
        return orderRepository.findAll();
    }

}
