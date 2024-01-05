package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.OrderItemDTO;
import com.example.transporter.model.entity.OrderItem;

import java.util.List;

public interface OrderItemMapper {

    OrderItemDTO toDTO(OrderItem orderItem);

    List<OrderItemDTO> toListDTO(List<OrderItem> orderItemList);

    OrderItem toEntity(OrderItemDTO orderItemDTO);
    
}
