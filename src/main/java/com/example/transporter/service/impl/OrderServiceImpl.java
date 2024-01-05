package com.example.transporter.service.impl;

import com.example.transporter.model.dto.ReportDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.repository.OrderRepository;
import com.example.transporter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findByAccount(Account account) {
        return orderRepository.findByAccountOrderByOrderDateDesc(account);
    }

    @Override
    public int totalPriceOrder() {
        return 0;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByProgress(String progress) {
        return orderRepository.findByProgress(progress);
    }

    @Override
    public List<Order> findByStatusIsTrue() {
        return orderRepository.findByStatusIsTrue();
    }

    @Override
    public List<Order> findReport(ReportDTO reportDTO) {
        String startDate = reportDTO.getStartDate() + " 00:00:00";
        String endDate = reportDTO.getEndDate() +  " 23:59:59";
        return orderRepository.findReport(startDate, endDate);
    }
}
