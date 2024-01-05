package com.example.transporter.service.impl;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.Shipping;
import com.example.transporter.repository.ShippingRepository;
import com.example.transporter.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<Shipping> findAll() {
        return shippingRepository.findAll();
    }

    @Override
    public Shipping findById(long id) {
        return shippingRepository.findById(id).orElse(null);
    }

    @Override
    public Shipping findByIdAndOrder(long id, Order order) {
        return shippingRepository.findByIdAndOrder(id, order);
    }

    @Override
    public List<Shipping> findByOrder(Order order) {
        return shippingRepository.findByOrder(order);
    }

    @Override
    public List<Shipping> findByAccount(Account account) {
        return shippingRepository.findByShipper(account);
    }

    @Override
    public Shipping save(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
}
