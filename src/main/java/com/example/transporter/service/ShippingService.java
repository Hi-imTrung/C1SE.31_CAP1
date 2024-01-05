package com.example.transporter.service;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.Shipping;

import java.util.List;

public interface ShippingService {

    List<Shipping> findAll();

    Shipping findById(long id);

    Shipping findByIdAndOrder(long id, Order order);

    List<Shipping> findByOrder(Order order);

    List<Shipping> findByAccount(Account account);

    Shipping save(Shipping shipping);

}
