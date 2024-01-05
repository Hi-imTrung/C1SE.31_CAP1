package com.example.transporter.service.impl;

import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.OrderItem;
import com.example.transporter.repository.OrderItemRepository;
import com.example.transporter.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
