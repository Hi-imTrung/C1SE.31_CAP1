package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.entity.Cart;

import java.util.List;

public interface CartMapper {

    CartDTO toDTO(Cart cart);

    List<CartDTO> toListDTO(List<Cart> cartList);

    Cart toEntity(CartDTO cartDTO);
    
}
