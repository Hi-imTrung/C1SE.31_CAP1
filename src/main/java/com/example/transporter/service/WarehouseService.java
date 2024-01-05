package com.example.transporter.service;

import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.entity.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> findAll();

    Warehouse findById(long id);

    Warehouse findByAddress(String address);

    Warehouse save(Warehouse warehouse);

}
