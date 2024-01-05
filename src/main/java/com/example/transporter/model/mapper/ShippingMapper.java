package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.ShippingDTO;
import com.example.transporter.model.entity.Shipping;

import java.util.List;

public interface ShippingMapper {
    
    ShippingDTO toDTO(Shipping shipping);

    List<ShippingDTO> toListDTO(List<Shipping> shippingList);

    Shipping toEntity(ShippingDTO shippingDTO);
    
}
