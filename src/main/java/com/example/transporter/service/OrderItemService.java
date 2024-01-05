package com.example.transporter.service;

import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findAll();

    OrderItem findById(long id);

    List<OrderItem> findByOrder(Order order);

    OrderItem save(OrderItem orderItem);

}
