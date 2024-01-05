package com.example.transporter.service.impl;

import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.entity.Warehouse;
import com.example.transporter.repository.WarehouseRepository;
import com.example.transporter.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse findByAddress(String address) {
        return warehouseRepository.findByAddress(address);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
}
