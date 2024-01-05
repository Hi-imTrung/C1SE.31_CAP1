package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.entity.Warehouse;
import com.example.transporter.model.mapper.WarehouseMapper;
import com.example.transporter.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseMapperImpl implements WarehouseMapper {

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public WarehouseDTO toDTO(Warehouse warehouse) {
        if (warehouse == null)
            return null;
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setId(warehouse.getId());
        warehouseDTO.setTitle(warehouse.getTitle());
        warehouseDTO.setAddress(warehouse.getAddress());
        warehouseDTO.setLocationLat(warehouse.getLocationLat());
        warehouseDTO.setLocationLng(warehouse.getLocationLng());
        warehouseDTO.setStatus(warehouse.isStatus());

        return warehouseDTO;
    }

    @Override
    public List<WarehouseDTO> toListDTO(List<Warehouse> warehouseList) {
        if (warehouseList == null)
            return null;

        List<WarehouseDTO> result = new ArrayList<>();
        warehouseList.forEach(warehouse -> {
            if (warehouse != null) {
                result.add(toDTO(warehouse));
            }
        });

        return result;
    }

    @Override
    public Warehouse toEntity(WarehouseDTO warehouseDTO) {
        if (warehouseDTO == null)
            return null;
        Warehouse warehouse = warehouseService.findById(warehouseDTO.getId());

        if (warehouse == null) {
            warehouse =  new Warehouse();
        }
        warehouse.setTitle(warehouseDTO.getTitle());
        warehouse.setAddress(warehouseDTO.getAddress());
        warehouse.setStatus(warehouseDTO.isStatus());

        return warehouse;
    }
}
