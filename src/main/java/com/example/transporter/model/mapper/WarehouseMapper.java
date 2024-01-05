package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper {

    WarehouseDTO toDTO(Warehouse warehouse);

    List<WarehouseDTO> toListDTO(List<Warehouse> warehouseList);

    Warehouse toEntity(WarehouseDTO warehouseDTO);
    
}
