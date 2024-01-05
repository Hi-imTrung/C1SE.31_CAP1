package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.CartItem;

import java.util.List;

public interface CartItemMapper {

    CartItemDTO toDTO(CartItem cartItem);

    List<CartItemDTO> toListDTO(List<CartItem> cartItemList);

    CartItem toEntity(CartItemDTO cartItemDTO);

}
