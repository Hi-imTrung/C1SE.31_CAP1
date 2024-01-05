package com.example.transporter.service;

import com.example.transporter.model.dto.ReportDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(long id);

    List<Order> findByAccount(Account account);

    int totalPriceOrder();

    Order save(Order order);

    List<Order> findByProgress(String progress);

    List<Order> findByStatusIsTrue();

    List<Order> findReport(ReportDTO reportDTO);

}
