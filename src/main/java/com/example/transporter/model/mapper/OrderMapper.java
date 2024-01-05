package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.model.entity.Order;

import java.util.List;

public interface OrderMapper {

    OrderDTO toDTO(Order order);

    List<OrderDTO> toListDTO(List<Order> orderList);

    Order toEntity(OrderDTO orderDTO);
    
}
